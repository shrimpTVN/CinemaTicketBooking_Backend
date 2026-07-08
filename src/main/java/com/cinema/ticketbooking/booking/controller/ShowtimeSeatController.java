package com.cinema.ticketbooking.booking.controller;

import com.cinema.ticketbooking.booking.service.IShowtimeSeatService;
import com.cinema.ticketbooking.booking.service.IShowtimeService;
import com.cinema.ticketbooking.dto.requestDto.ShowtimeSeatRequestDto;
import com.cinema.ticketbooking.dto.responseDto.ShowtimeResponseDto;
import com.cinema.ticketbooking.dto.responseDto.ShowtimeSeatResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showtime-seats")
@RequiredArgsConstructor
public class ShowtimeSeatController {
    private final IShowtimeSeatService showtimeSeatService;
    private final IShowtimeService showtimeService;

    @GetMapping("/initialize-showtime-seats")
    public ResponseEntity<String> initializeShowtimeSeats() {
        List<ShowtimeResponseDto> showtimeDtos = showtimeService.getAllShowtimes();
//        System.out.println("Initializing showtime seats for " + showtimeDtos.size() + " showtimes.");
        showtimeDtos.forEach(showtime -> {
//            System.out.println("Initializing seats for showtime ID: " + showtime.id() + ", Hall ID: " + showtime.hallId());
            showtimeSeatService.initializeInventoryForShowtime(showtime.id(), showtime.hallId());
        });

        return ResponseEntity.ok("All showtime seats initialized successfully.");
    }

    @GetMapping("/showtimes/{id}")
    public ResponseEntity<List<ShowtimeSeatResponseDto>> getSeatsForShowtime(@PathVariable Integer id) {
        List<ShowtimeSeatResponseDto> seats = showtimeSeatService.getSeatsForShowtime(id);
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/showtimes/{id}/available-count")
    public ResponseEntity<Integer> countAvailableSeatsByShowtimeId(@PathVariable Integer id) {
        Integer availableCount = showtimeSeatService.countAvailableSeatsByShowtimeId(id);
        return ResponseEntity.ok(availableCount);
    }

    @PostMapping("/showtimes/{showtimeId}/hold")
    public ResponseEntity<String> holdSeats(@PathVariable Integer showtimeId, @RequestBody ShowtimeSeatRequestDto requestDto) {
        showtimeSeatService.holdSeats(showtimeId, requestDto.seatIds(), requestDto.userId());
        return ResponseEntity.ok("Seats held successfully");
    }

    @PostMapping("/showtimes/{showtimeId}/release")
    public ResponseEntity<String> releaseSeats(@PathVariable Integer showtimeId, @RequestBody ShowtimeSeatRequestDto requestDto) {
        showtimeSeatService.releaseSeats(showtimeId, requestDto.seatIds(), requestDto.userId());
        return ResponseEntity.ok("Seats released successfully");
    }

}
