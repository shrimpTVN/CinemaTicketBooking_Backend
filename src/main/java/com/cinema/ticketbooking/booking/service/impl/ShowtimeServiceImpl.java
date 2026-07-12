package com.cinema.ticketbooking.booking.service.impl;

import com.cinema.ticketbooking.booking.service.IShowtimeSeatService;
import com.cinema.ticketbooking.booking.service.IShowtimeService;
import com.cinema.ticketbooking.core.exception.custom.ResourceNotFoundException;
import com.cinema.ticketbooking.dto.requestDto.ShowtimeRequestDto;
import com.cinema.ticketbooking.dto.responseDto.ShowtimeResponseDto;
import com.cinema.ticketbooking.entity.Hall;
import com.cinema.ticketbooking.entity.Movie;
import com.cinema.ticketbooking.entity.Showtime;
import com.cinema.ticketbooking.repository.HallRepository;
import com.cinema.ticketbooking.repository.MovieRepository;
import com.cinema.ticketbooking.repository.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements IShowtimeService {
    private final HallRepository hallRepository;
    private final ShowtimeRepository showtimeRepository;
    private final MovieRepository movieRepository;
    private final IShowtimeSeatService showtimeSeatService;

    @Override
    public List<ShowtimeResponseDto> getAllShowtimes() {
        List<Showtime> showtimes = showtimeRepository.findAll();
        return showtimes.stream().map(this::transformToDto).toList();
    }

    @Override
    public ShowtimeResponseDto getShowtimeById(Integer id) {
        Showtime showtime = showtimeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Showtime not found with id: " + id));
        return transformToDto(showtime);
    }

    @Override
    public List<ShowtimeResponseDto> filterShowtimes(Integer movieId, LocalDate date, Integer hallId) {
        LocalDateTime now = LocalDateTime.now();
        return showtimeRepository.findAll().stream()
                .filter(showtime -> movieId == null || showtime.getMovie().getId().equals(movieId))
                .filter(showtime -> date == null || showtime.getDate().equals(date))
                .filter(showtime -> hallId == null || showtime.getHall().getId().equals(hallId))
                .filter(showtime -> LocalDateTime.of(showtime.getDate(), showtime.getStartTime()).isAfter(now))
                .map(this::transformToDto)
                .toList();
    }

    @Override
    public List<ShowtimeResponseDto> filterShowtimes(Integer movieId, LocalDate date) {
        LocalDateTime now = LocalDateTime.now();
        return showtimeRepository.findAll().stream()
                .filter(showtime -> movieId == null || showtime.getMovie().getId().equals(movieId))
                .filter(showtime -> date == null || showtime.getDate().equals(date))
                .filter(showtime -> LocalDateTime.of(showtime.getDate(), showtime.getStartTime()).isAfter(now))
                .map(this::transformToDto)
                .toList();
    }

    @Override
    public List<ShowtimeResponseDto> getShowtimesByMovieId(Integer movieId) {
        LocalDateTime now = LocalDateTime.now();
        List<Showtime> showtimes = showtimeRepository.findByMovieId(movieId);
        return showtimes.stream().filter(showtime -> LocalDateTime.of(showtime.getDate(), showtime.getStartTime()).isAfter(now))
                .map(this::transformToDto).toList();
    }

    @Override
    public List<ShowtimeResponseDto> getShowtimesByHallId(Integer hallId) {
        LocalDateTime now = LocalDateTime.now();
        List<Showtime> showtimes = showtimeRepository.findByHallId(hallId);
        return showtimes.stream().filter(showtime -> LocalDateTime.of(showtime.getDate(), showtime.getStartTime()).isAfter(now))
                .map(this::transformToDto).toList();
    }

    @Override
    public List<ShowtimeResponseDto> getShowtimesByDate(LocalDate date) {
        LocalDateTime now = LocalDateTime.now();
        List<Showtime> showtimes = showtimeRepository.findByDate(date);
        return showtimes.stream().filter(showtime -> LocalDateTime.of(showtime.getDate(), showtime.getStartTime()).isAfter(now))
                .map(this::transformToDto).toList();
    }


    @Override
    public ShowtimeResponseDto createShowtime(ShowtimeRequestDto showtime) {
        Showtime newShowtime = new Showtime();
        Hall hall = hallRepository.findById(showtime.hallId()).orElseThrow(() -> new ResourceNotFoundException("Hall not found with id: " + showtime.hallId()));
        Movie movie = movieRepository.findById(showtime.movieId()).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + showtime.movieId()));

        newShowtime.setHall(hall);
        newShowtime.setMovie(movie);
        newShowtime.setDate(showtime.date());
        newShowtime.setStartTime(showtime.startTime());
        newShowtime.setType(showtime.type());
        Showtime savedShowtime = showtimeRepository.save(newShowtime);
        showtimeSeatService.initializeInventoryForShowtime(savedShowtime.getId(), hall.getId());
        return transformToDto(savedShowtime);
    }

    @Override
    public ShowtimeResponseDto updateShowtime(Integer id, ShowtimeRequestDto showtime) {
        Showtime existingShowtime = showtimeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Showtime not found with id: " + id));

        if (showtime.startTime() != null) existingShowtime.setStartTime(showtime.startTime());
        if (showtime.date() != null) existingShowtime.setDate(showtime.date());
        if (showtime.type() != null) existingShowtime.setType(showtime.type());

        Showtime updatedShowtime = showtimeRepository.save(existingShowtime);
        return transformToDto(updatedShowtime);
    }

    @Override
    public void deleteShowtime(Integer id) {
        showtimeRepository.deleteById(id);
    }

    private ShowtimeResponseDto transformToDto(Showtime showtime) {
        return new ShowtimeResponseDto(showtime.getId(), showtime.getHall().getId(), showtime.getHall().getName(),
                showtime.getMovie().getId(), showtime.getMovie().getTitle(), showtime.getDate(), showtime.getStartTime(), showtime.getType());
    }

}