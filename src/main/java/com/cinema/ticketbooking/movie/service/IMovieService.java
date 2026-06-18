package com.cinema.ticketbooking.movie.service;

import com.cinema.ticketbooking.dto.MovieDto;

import java.util.List;

public interface IMovieService {

    public List<MovieDto> getAllMovies();
}
