package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Integer> {
}