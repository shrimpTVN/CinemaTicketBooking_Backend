package com.cinema.ticketbooking.hall.service;

import com.cinema.ticketbooking.dto.HallDto;
import com.cinema.ticketbooking.dto.SeatDto;

import java.util.List;

public interface IHallService {
    public List<HallDto> getAllHalls();
    public HallDto getHallById(int id);
    public HallDto createHall(HallDto hallDto);
    public HallDto updateHall(int id, HallDto hallDto);

    public List<SeatDto> getHallSeatMap(int id);
    public List<SeatDto> generateHallSeatMap(int id, List<SeatDto> seatDtos);
    public List<SeatDto> updateHallSeatMap(int id, List<SeatDto> seatDtos);

}
