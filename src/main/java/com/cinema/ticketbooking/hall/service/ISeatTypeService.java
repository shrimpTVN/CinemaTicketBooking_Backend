package com.cinema.ticketbooking.hall.service;

import com.cinema.ticketbooking.dto.SeatTypeDto;

import java.util.List;

public interface ISeatTypeService {
    public List<SeatTypeDto> getAllSeatTypes();
    public SeatTypeDto getSeatTypeById(Integer id);
    public SeatTypeDto createSeatType(SeatTypeDto seatTypeDto);
    public SeatTypeDto updateSeatType(Integer id, SeatTypeDto seatTypeDto);
}
