package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    public List<Seat> findByHallId(Integer hallId);
}