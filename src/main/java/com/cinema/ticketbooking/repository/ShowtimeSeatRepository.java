package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.ShowtimeSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeSeatRepository extends JpaRepository<ShowtimeSeat, Long> {
}