package com.cinema.ticketbooking.booking.service;

import com.cinema.ticketbooking.dto.responseDto.ShowtimeSeatResponseDto;

import java.util.List;

public interface IShowtimeSeatService {

    public void initializeInventoryForShowtime(Integer showtimeId, Integer hallId);

    public List<ShowtimeSeatResponseDto> getSeatsForShowtime(Integer showtimeId);

    public Integer countAvailableSeatsByShowtimeId(Integer id);

    // UPDATE: The Concurrency Hotspots
    public void holdSeats(Integer showtimeId, List<Integer> seatIds, Integer userId);

    public void confirmBooking(Integer showtimeId, List<Integer> seatIds, Integer userId);

    public void releaseSeats(Integer showtimeId, List<Integer> seatIds, Integer userId);
    public void releaseAllSeatsHoldByUser(Integer userId);

    public void releaseExpiredHolds();
}
