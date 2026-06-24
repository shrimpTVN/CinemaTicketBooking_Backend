package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {
}