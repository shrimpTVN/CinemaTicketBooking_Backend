package com.cinema.ticketbooking.booking.service;

import com.cinema.ticketbooking.dto.requestDto.ShowtimeRequestDto;
import com.cinema.ticketbooking.dto.responseDto.ShowtimeResponseDto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface IShowtimeService {

    public List<ShowtimeResponseDto> getAllShowtimes();
    public ShowtimeResponseDto getShowtimeById(Integer id);
    public List<ShowtimeResponseDto> filterShowtimes(Integer movieId, LocalDate date, Integer hallId);
    public List<ShowtimeResponseDto> filterShowtimes(Integer movieId, LocalDate date);
    public List<ShowtimeResponseDto> getShowtimesByMovieId(Integer movieId);
    public List<ShowtimeResponseDto> getShowtimesByHallId(Integer hallId);
    public List<ShowtimeResponseDto> getShowtimesByDate(LocalDate date);

    public ShowtimeResponseDto createShowtime(ShowtimeRequestDto showtime);
    public ShowtimeResponseDto updateShowtime(Integer id, ShowtimeRequestDto showtime);
    public void deleteShowtime(Integer id);


}
