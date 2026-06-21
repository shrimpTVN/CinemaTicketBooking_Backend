package com.cinema.ticketbooking.movie.service;

import com.cinema.ticketbooking.dto.GenreDto;

import java.util.List;

public interface IGenreService {
    public List<GenreDto> getAllGenres();
    public  GenreDto getGenreById(long id);
    public GenreDto createGenre(GenreDto genreDto);
    public GenreDto updateGenre(Long id, GenreDto genreDto);
    public boolean deleteGenre(long id);
}
