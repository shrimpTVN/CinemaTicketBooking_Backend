package com.cinema.ticketbooking.hall.service;

import com.cinema.ticketbooking.dto.SeatDto;
import com.cinema.ticketbooking.dto.requestDto.HallRequestDto;
import com.cinema.ticketbooking.dto.responseDto.HallResponseDto;

import java.util.List;

public interface IHallService {
    public List<HallResponseDto> getAllHalls();
    public HallResponseDto getHallById(int id);
    public HallResponseDto createHall(HallRequestDto hallDto);
    public HallResponseDto updateHall(int id, HallRequestDto hallDto);

    public String updateHallStatus(int id, String status);

    public List<SeatDto> getHallSeatMap(int id);
    public List<SeatDto> generateHallSeatMap(int id, List<SeatDto> seatDtos);
    public List<SeatDto> updateHallSeatMap(int id, List<SeatDto> seatDtos);

}
