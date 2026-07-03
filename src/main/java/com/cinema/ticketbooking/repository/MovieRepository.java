package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    public List<Movie> findByStatus(String status);
}