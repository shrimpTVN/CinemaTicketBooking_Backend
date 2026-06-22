package com.cinema.ticketbooking.hall.service;

import com.cinema.ticketbooking.dto.HallDto;

import java.util.List;

public interface IHallService {
    public List<HallDto> getAllHalls();
    public HallDto getHallById(int id);
    public HallDto createHall(HallDto hallDto);
    public HallDto updateHall(int id, HallDto hallDto);

}
