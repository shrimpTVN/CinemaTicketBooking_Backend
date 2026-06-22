package com.cinema.ticketbooking.movie.service;

import com.cinema.ticketbooking.dto.MovieDto;

import java.util.List;

public interface IMovieService {

    public List<MovieDto> getAllMovies();

    public MovieDto getMovieById(Long id);

    public MovieDto createMovie(MovieDto movieDto);

    public MovieDto updateMovie(Long id, MovieDto movieDto);

//    public MovieDto getMovieByName(String name);
//    public boolean deleteMovie(Long id);

}
