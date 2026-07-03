package com.cinema.ticketbooking.movie.service;

import com.cinema.ticketbooking.dto.MovieDto;

import java.util.ArrayList;
import java.util.List;

public interface IMovieService {

    public List<MovieDto> getAllMovies();

    public List<MovieDto> getMovieByCode(String type);

    public MovieDto getMovieById(Integer id);

    public MovieDto createMovie(MovieDto movieDto);

    public MovieDto updateMovie(Integer id, MovieDto movieDto);


//    public MovieDto getMovieByName(String name);
//    public boolean deleteMovie(Long id);

}
