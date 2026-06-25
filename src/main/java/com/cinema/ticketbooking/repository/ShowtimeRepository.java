package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {

    public List<Showtime> findByMovieId(Integer movieId);
    public List<Showtime> findByHallId(Integer hallId);
    public List<Showtime> findByDate(LocalDate date);
}