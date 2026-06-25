package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}