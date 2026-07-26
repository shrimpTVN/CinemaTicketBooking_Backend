package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.CommentRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRatingRepository extends JpaRepository<CommentRating, Integer> {

    public List<CommentRating> findByMovieId(int movieId);
}