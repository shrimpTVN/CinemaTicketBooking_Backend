package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatTypeRepository extends JpaRepository<SeatType, Integer> {
}