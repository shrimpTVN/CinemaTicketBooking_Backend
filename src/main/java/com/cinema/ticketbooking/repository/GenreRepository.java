package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}
