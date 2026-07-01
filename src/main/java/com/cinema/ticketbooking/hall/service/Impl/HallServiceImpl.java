package com.cinema.ticketbooking.hall.service.Impl;

import com.cinema.ticketbooking.core.exception.ResourceNotFoundException;
import com.cinema.ticketbooking.dto.requestDto.HallRequestDto;
import com.cinema.ticketbooking.dto.responseDto.HallResponseDto;
import com.cinema.ticketbooking.dto.SeatDto;
import com.cinema.ticketbooking.entity.Hall;
import com.cinema.ticketbooking.entity.HallType;
import com.cinema.ticketbooking.entity.Seat;
import com.cinema.ticketbooking.entity.SeatType;
import com.cinema.ticketbooking.hall.service.IHallService;
import com.cinema.ticketbooking.repository.HallRepository;
import com.cinema.ticketbooking.repository.HallTypeRepository;
import com.cinema.ticketbooking.repository.SeatRepository;
import com.cinema.ticketbooking.repository.SeatTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HallServiceImpl  implements IHallService {
    private final HallRepository hallRepository;
    private final HallTypeRepository hallTypeRepository;
    private final SeatTypeRepository seatTypeRepository;
    private final SeatRepository seatRepository;

    @Override
    public List<HallResponseDto> getAllHalls() {
        List<Hall> halls = hallRepository.findAll();
        return halls.stream().map(this::transformToDto).toList();
    }

    @Override
    public HallResponseDto getHallById(int id) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall not found with id: " + id));
        return transformToDto(hall);
    }

    @Override
    public HallResponseDto createHall(HallRequestDto hallDto) {
        Hall hall = new Hall();
        hall.setName(hallDto.name());
        hall.setWidth(hallDto.width());
        hall.setHeight(hallDto.height());

        HallType hallType = hallTypeRepository.findById(hallDto.hallTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Hall type not found with id: " + hallDto.hallTypeId()));
        hall.setHallType(hallType);

        hallRepository.save(hall);

        return transformToDto(hall);
    }

    @Override
    public HallResponseDto updateHall(int id, HallRequestDto hallDto) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall not found with id: " + id));
        if (hallDto.name() != null) {
            hall.setName(hallDto.name());
        }
        if (hallDto.width() != null) {
            hall.setWidth(hallDto.width());
        }
        if (hallDto.height() != null) {
            hall.setHeight(hallDto.height());
        }

        hallRepository.save(hall);
        return transformToDto(hall);
    }

    @Override
    public String updateHallStatus(int id, String status) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall not found with id: " + id));
        hall.setStatus(status);
        hallRepository.save(hall);
        return "Hall status updated successfully";
    }

//    You fetch the Hall entity just to access its getSeats() collection.
//    @Override
//    public List<SeatDto> getHallSeatMap(int id) {
//        Hall hall = hallRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Hall not found with id: " + id));
//        @NonNull Set<Seat> seats = hall.getSeats();
//        return seats.stream().map(this::transformToDto).toList();
//    }

    // Better approach
    @Override
    public List<SeatDto> getHallSeatMap(int id) {
        return seatRepository.findByHallId(id)
                .stream()
                .map(this::transformToDto)
                .toList();
    }

//    This is the dreaded N+1 Query Problem
//    @Override
//    public List<SeatDto> generateHallSeatMap(int id, List<SeatDto> seatDtos) {
//        Set<Seat> seats = new HashSet<>();
//        Hall hall = hallRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Hall not found with id: " + id));
//
//        seatDtos.forEach(seatDto -> {
//            Seat seat = new Seat();
//            // Set seat properties based on seatDto
//            SeatType seatType = seatTypeRepository.findById(seatDto.seatTypeId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Seat type not found with id: " + seatDto.seatTypeId()));
//            seat.setSeatType(seatType);
//            seat.setRowLabel(seatDto.rowLabel());
//            seat.setColNumber(seatDto.colNumber());
//            seat.setStatus("ON");
//            seats.add(seat);
//        });
//        hall.setSeats(seats);
//        hallRepository.save(hall);
//        return List.of();
//    }

//    better approach
    @Override
    @Transactional // Ensures ACID compliance. Either all seats save, or none do.
    public List<SeatDto> generateHallSeatMap(int id, List<SeatDto> seatDtos) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall not found"));

        // 1. Extract all unique SeatType IDs from the incoming payload
        Set<Integer> seatTypeIds = seatDtos.stream()
                .map(SeatDto::seatTypeId)
                .collect(Collectors.toSet());

        // 2. Fetch all required SeatTypes in ONE query (e.g., SELECT * FROM seat_type WHERE id IN (...))
        // Store them in a Map for instant O(1) memory lookup inside the loop
        Map<Integer, SeatType> seatTypeMap = seatTypeRepository.findAllById(seatTypeIds).stream()
                .collect(Collectors.toMap(SeatType::getId, type -> type));

        Set<Seat> newSeats = new HashSet<>();

        for (SeatDto dto : seatDtos) {
            SeatType type = seatTypeMap.get(dto.seatTypeId());//get seatType from map<id, seatType> instead of query from the DB
            if (type == null) {
                throw new ResourceNotFoundException("Seat type not found: " + dto.seatTypeId());
            }

            Seat seat = new Seat();
            seat.setHall(hall); // CRITICAL: Set the owning side of the relationship
            seat.setSeatType(type);
            seat.setRowLabel(dto.rowLabel());
            seat.setColNumber(dto.colNumber());
            seat.setStatus("ON");

            newSeats.add(seat);
        }

        hall.getSeats().addAll(newSeats);

        // We only call save once. Hibernate will batch insert the seats.
        hallRepository.save(hall);

        return newSeats.stream().map(this::transformToDto).toList();
    }

//    N+1 Queries Multiplied
//    Missing @Transactional
//    Security/Data Leakage
//    Hibernate Dirty Checking
//    @Override
//    public List<SeatDto> updateHallSeatMap(int id, List<SeatDto> seatDtos) {
//        Hall hall = hallRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Hall not found with id: " + id));
//        seatDtos.stream().map(seatDto -> {
//            Seat seat = seatRepository.findById(seatDto.id())
//                    .orElseThrow(() -> new ResourceNotFoundException("Seat not found with id: " + seatDto.id()));
//            if (seatDto.rowLabel() != null) {
//                seat.setRowLabel(seatDto.rowLabel());
//            }
//            if (seatDto.colNumber() != null) {
//                seat.setColNumber(seatDto.colNumber());
//            }
//            if (seatDto.status() != null) {
//                seat.setStatus(seatDto.status());
//            }
//            if (seatDto.seatTypeId() != null) {
//                SeatType seatType = seatTypeRepository.findById(seatDto.seatTypeId())
//                        .orElseThrow(() -> new ResourceNotFoundException("Seat type not found with id: " + seatDto.seatTypeId()));
//                seat.setSeatType(seatType);
//            }
//            seatRepository.save(seat);
//            return transformToDto(seat);
//        }).toList();
//        return seatDtos;
//    }
//  Better approach
    @Override
    @Transactional
    public List<SeatDto> updateHallSeatMap(int id, List<SeatDto> seatDtos) {
        // 1. Fetch all existing seats for THIS hall to ensure security
        Map<Integer, Seat> existingSeatsMap = seatRepository.findByHallId(id).stream()
                .collect(Collectors.toMap(Seat::getId, seat -> seat));

        // 2. Fetch required SeatTypes in batch (Same optimization as above)
        Set<Integer> seatTypeIds = seatDtos.stream()
                .map(SeatDto::seatTypeId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Map<Integer, SeatType> seatTypeMap = seatTypeRepository.findAllById(seatTypeIds).stream()
                .collect(Collectors.toMap(SeatType::getId, type -> type));

        List<Seat> updatedSeats = new ArrayList<>();

        for (SeatDto dto : seatDtos) {
            // SECURITY: Only allow updating seats that actually belong to this hall
            Seat seatToUpdate = existingSeatsMap.get(dto.id());
            if (seatToUpdate == null) {
                throw new IllegalArgumentException("Seat ID " + dto.id() + " does not belong to Hall " + id);
            }

            if (dto.rowLabel() != null) seatToUpdate.setRowLabel(dto.rowLabel());
            if (dto.colNumber() != null) seatToUpdate.setColNumber(dto.colNumber());
            if (dto.status() != null) seatToUpdate.setStatus(dto.status());

            if (dto.seatTypeId() != null) {
                SeatType type = seatTypeMap.get(dto.seatTypeId());
                if (type == null) throw new ResourceNotFoundException("Seat type not found");
                seatToUpdate.setSeatType(type);
            }

            updatedSeats.add(seatToUpdate);
        }

        // Notice: NO seatRepository.save() in the loop.
        // Because of @Transactional, Hibernate's Dirty Checking detects the modifications
        // and automatically fires UPDATE statements when the transaction commits.

        return updatedSeats.stream().map(this::transformToDto).toList();
    }
    private HallResponseDto transformToDto(Hall hall){
        return new HallResponseDto(hall.getId(), hall.getName(), hall.getWidth(),
                hall.getHeight(),hall.getHallType().getName(), hall.getStatus());
    }

    private SeatDto transformToDto(Seat seat){
        return new SeatDto(seat.getId(), seat.getSeatType().getId(), seat.getRowLabel(), seat.getColNumber(), seat.getStatus());
   }

}
