package com.cinema.ticketbooking.booking.service.impl;

import com.cinema.ticketbooking.booking.service.IShowtimeSeatService;
import com.cinema.ticketbooking.core.exception.custom.ConcurrentSeatBookingException;
import com.cinema.ticketbooking.dto.responseDto.ShowtimeSeatResponseDto;
import com.cinema.ticketbooking.dto.websocket.ShowtimeSeatUpdateEventDto;
import com.cinema.ticketbooking.entity.*;
import com.cinema.ticketbooking.repository.HallRepository;
import com.cinema.ticketbooking.repository.SeatRepository;
import com.cinema.ticketbooking.repository.ShowtimeRepository;
import com.cinema.ticketbooking.repository.ShowtimeSeatRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowtimeSeatServiceImpl implements IShowtimeSeatService {

    private final ShowtimeSeatRepository showtimeSeatRepository;
    private final SeatRepository seatRepository;
    private final ShowtimeRepository showtimeRepository;
    private final HallRepository hallRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void initializeInventoryForShowtime(Integer showtimeId, Integer hallId) {
        if (!showtimeSeatRepository.findByShowtimeId(showtimeId).isEmpty()) {
            log.info("Showtime seats already initialized for showtime ID: {}", showtimeId);
            return;
        }

        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new IllegalArgumentException("Showtime not found for ID: " + showtimeId));

        Hall hall = hallRepository.findById(hallId)
                .orElseThrow(() -> new IllegalArgumentException("Hall not found for ID: " + hallId));

        List<ShowtimeSeat> showtimeSeats = new ArrayList<>();

        hall.getSeats().forEach(seat -> {
            ShowtimeSeat showtimeSeat = new ShowtimeSeat();
//            System.out.println("Initializing seat: " + seat.getId() + " for showtime: " + showtimeId);
            showtimeSeat.setId(new ShowtimeSeatId(showtimeId, seat.getId()));
            showtimeSeat.setSeat(seat);
            showtimeSeat.setShowtime(showtime);
            showtimeSeat.setStatus("AVAILABLE");
            showtimeSeats.add(showtimeSeat);
        });
//        System.out.println("Total seats initialized for showtime " + showtimeId + ": " + showtimeSeats.size());
        showtimeSeatRepository.saveAll(showtimeSeats);

    }

    @Override
    public List<ShowtimeSeatResponseDto> getSeatsForShowtime(Integer showtimeId) {
        List<ShowtimeSeat> showtimeSeats = showtimeSeatRepository.findSeatMapByShowtimeId(showtimeId);
        return showtimeSeats.stream().map(this::transformToDto).collect(Collectors.toList());
    }

    @Override
    public Integer countAvailableSeatsByShowtimeId(Integer id) {
        return showtimeSeatRepository.countAvailableSeatsByShowtimeId(id);
    }

    private List<ShowtimeSeat> getShowtimeSeats(Integer showtimeId, List<Integer> seatIds) {
        // 1. Build the composite keys
        List<ShowtimeSeatId> ids = seatIds.stream()
                .map(seatId -> new ShowtimeSeatId(showtimeId, seatId))
                .collect(Collectors.toList());

        // 2. Fetch the current state of the requested seats
        List<ShowtimeSeat> requestedSeats = showtimeSeatRepository.findAllById(ids);

        // 3. Validate inventory exists and matches request size
        if (requestedSeats.size() != seatIds.size()) {
            throw new IllegalArgumentException("One or more requested seats do not exist for this showtime.");
        }
        return requestedSeats;
    }

    @Override
    @Transactional
    public void holdSeats(Integer showtimeId, List<Integer> seatIds, Integer userId) {
         List<ShowtimeSeat> requestedSeats = getShowtimeSeats(showtimeId, seatIds);

        // 4. Process the state transition
        for (ShowtimeSeat seat : requestedSeats) {
            if (!"AVAILABLE".equals(seat.getStatus())) {
                throw new ConcurrentSeatBookingException("Seat " + seat.getId().getSeatId() + " is no longer available.");
            }

            seat.setStatus("HELD");
            seat.setHoldBy(userId);
            // Hold the seat for exactly 5 minutes
            seat.setHoldUntil(LocalDateTime.now().plusMinutes(5));
        }

        // 5. Attempt to save. This is where Optimistic Locking kicks in!
        try {
            showtimeSeatRepository.saveAll(requestedSeats);

            // 2. BROADCAST: Tell everyone viewing this showtime that seats are on HOLD
            broadcastToSubscribedUser(showtimeId, seatIds, "HELD", userId);

        } catch (ObjectOptimisticLockingFailureException e) {
            log.warn("Concurrency collision detected for showtime: {}", showtimeId);
            // In a real app, throw a custom exception so the GlobalExceptionHandler
            // can return a 409 Conflict to the React JS frontend.
            throw new ConcurrentSeatBookingException("Another user just took one of these seats. Please refresh and try again.");
        }
    }

    @Override
    @Transactional
    public void confirmBooking(Integer showtimeId, List<Integer> seatIds, Integer userId) {
        List<ShowtimeSeat> requestedSeats = getShowtimeSeats(showtimeId, seatIds);
        requestedSeats.forEach(seat -> {
//            only confirm the seat when the current user is who already hold the seat and the seat status is held
            if (!Objects.equals(seat.getHoldBy(), userId) || !seat.getStatus().equals("HELD")) {
                throw new ConcurrentSeatBookingException("Seat " + seat.getId().getSeatId() + " is not held by user: " + userId);
            }
            seat.setStatus("BOOKED");
            seat.setHoldBy(userId);
            seat.setHoldUntil(null);


        });
        try {
            showtimeSeatRepository.saveAll(requestedSeats);
            // 2. BROADCAST: Tell everyone viewing this showtime that seats are on BOOKED
            broadcastToSubscribedUser(showtimeId, seatIds, "BOOKED", userId);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new RuntimeException("Error while confirming showtime seat booking.");
        }
    }

    @Override
    @Transactional
    public void releaseSeats(Integer showtimeId, List<Integer> seatIds, Integer userId) {
        List<ShowtimeSeat> requestedSeats = getShowtimeSeats(showtimeId, seatIds);
        requestedSeats.forEach(seat -> {
            //only release the showtime-sea that held by this user and in the "HELD" status
            if (!Objects.equals(seat.getHoldBy(), userId) || !"HELD".equals(seat.getStatus())) {
                System.out.println("Releasing seat " + seat.getId().getSeatId() + " held by user: " + seat.getHoldBy());
                throw new ConcurrentSeatBookingException("Seat " + seat.getId().getSeatId() + " is no held by user : " + userId);
            }
            seat.setStatus("AVAILABLE");
            seat.setHoldBy(0);
            seat.setHoldUntil(null);


        });
        try {
            showtimeSeatRepository.saveAll(requestedSeats);
            // 2. BROADCAST: Tell everyone viewing this showtime that seats are on AVAILABLE
            broadcastToSubscribedUser(showtimeId, seatIds, "AVAILABLE", userId);
        } catch (ObjectOptimisticLockingFailureException e) {

            throw new RuntimeException("Error while release showtime seat.");
        }
    }

    @Override
    public void releaseAllSeatsHoldByUser(Integer userId) {
        List<ShowtimeSeat> heldSeats = showtimeSeatRepository.findAllByHoldByAndStatus(userId, "HELD");
        if (heldSeats.isEmpty()) {
            return;
        }
        Integer showtimeId =  heldSeats.getFirst().getId().getSeatId();
        List<Integer> seatIds = new ArrayList<>();
        heldSeats.forEach(seat -> {
            if ( !seat.getStatus().equals("HELD")) {
                throw new ConcurrentSeatBookingException("Seat " + seat.getId().getSeatId() + " is not held by user: " + userId);
            }
            seat.setStatus("AVAILABLE");
            seat.setHoldBy(0);
            seat.setHoldUntil(null);
            seatIds.add(seat.getId().getSeatId());
        });
        try {
            showtimeSeatRepository.saveAll(heldSeats);
            // 2. BROADCAST: Tell everyone viewing this showtime that seats are on AVAILABLE
            broadcastToSubscribedUser(showtimeId, seatIds, "AVAILABLE", userId);

        } catch (ObjectOptimisticLockingFailureException e) {
            throw new RuntimeException("Error while releasing all seats held by user: " + userId);
        }
    }

    //update message broadcast
    @Override
    @Transactional
    public void releaseExpiredHolds() {
        int releasedCount = showtimeSeatRepository.releaseExpiredHolds(Instant.now());
        log.info("Released {} expired holds.", releasedCount);
    }

    private ShowtimeSeatResponseDto transformToDto(ShowtimeSeat showtimeSeat){
        return new ShowtimeSeatResponseDto(showtimeSeat.getId().getShowtimeId(),
                showtimeSeat.getId().getSeatId(), showtimeSeat.getStatus());
    }

    private void broadcastToSubscribedUser(Integer showtimeId, List<Integer> seatIds, String status, Integer userId) {
        ShowtimeSeatUpdateEventDto event = new ShowtimeSeatUpdateEventDto(showtimeId, seatIds, status, userId);
        messagingTemplate.convertAndSend("/topic/showtimes/" + showtimeId + "/seats", event);
    }

}