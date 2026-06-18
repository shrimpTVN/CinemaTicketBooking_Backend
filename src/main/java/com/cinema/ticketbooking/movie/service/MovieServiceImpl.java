package com.cinema.ticketbooking.movie.service;

import com.cinema.ticketbooking.dto.MovieDto;
import com.cinema.ticketbooking.entity.Movie;
import com.cinema.ticketbooking.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements IMovieService{

    private final MovieRepository movieRespository;


    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movieList = movieRespository.findAll();
        List<MovieDto> movieDtoList = new ArrayList<>();
        movieList.forEach(movie -> { movieDtoList.add(transformToDto(movie));});
        return movieDtoList;
    }

    private MovieDto transformToDto(Movie movie) {
        return new MovieDto(movie.getId(), movie.getTitle(), movie.getDuration(),
                movie.getAvatar(), movie.getTrailer(), movie.getDescription(), movie.getCountry(),
                movie.getAgeLimit(), movie.getPremiereDate(), movie.getRating(), movie.getActors(),
                movie.getDirector(), movie.getGenres(), movie.getStatus());
    }
}
