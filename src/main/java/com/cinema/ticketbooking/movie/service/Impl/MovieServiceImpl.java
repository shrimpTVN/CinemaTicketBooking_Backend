package com.cinema.ticketbooking.movie.service.Impl;

import com.cinema.ticketbooking.core.exception.ResourceNotFoundException;
import com.cinema.ticketbooking.dto.GenreDto;
import com.cinema.ticketbooking.dto.MovieDto;
import com.cinema.ticketbooking.entity.Genre;
import com.cinema.ticketbooking.entity.Movie;
import com.cinema.ticketbooking.movie.service.IGenreService;
import com.cinema.ticketbooking.movie.service.IMovieService;
import com.cinema.ticketbooking.repository.GenreRepository;
import com.cinema.ticketbooking.repository.MovieRepository;
import com.cinema.ticketbooking.repository.SpecialListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements IMovieService {

    private final MovieRepository movieRespository;
    private final IGenreService genreService;
    private final GenreRepository genreRepository;
    private final SpecialListRepository specialListRepository;

    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movieList = movieRespository.findAll();
        List<MovieDto> movieDtoList = new ArrayList<>();
        movieList.forEach(movie -> {
            movieDtoList.add(transformToDto(movie));
        });
        return movieDtoList;
    }

    @Override
    public List<MovieDto> getMovieByCode(String type) {
        List<Integer>  listId = specialListRepository.findByCode(type).getList();
        System.out.println(listId);
        List<MovieDto> movieDtoList = new ArrayList<>();
        listId.forEach(id -> {
            Movie movie = movieRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
            movieDtoList.add(transformToDto(movie));
        });
        return movieDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public MovieDto getMovieById(Integer id) {
        if (movieRespository.existsById(id)) {
            Movie movie = movieRespository.getReferenceById(id);
            return transformToDto(movie);
        }
        return null;
    }


    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        // Basic validation to avoid persisting invalid rows that violate DB constraints
        if (movieDto == null) throw new IllegalArgumentException("Movie payload is required");
        if (movieDto.title() == null || movieDto.title().isBlank())
            throw new IllegalArgumentException("title is required");
        if (movieDto.duration() == null || movieDto.duration() <= 0)
            throw new IllegalArgumentException("duration must be > 0");
        if (movieDto.avatar() == null || movieDto.avatar().isBlank())
            throw new IllegalArgumentException("avatar is required");
        if (movieDto.trailer() == null || movieDto.trailer().isBlank())
            throw new IllegalArgumentException("trailer is required");
        if (movieDto.country() == null || movieDto.country().isBlank())
            throw new IllegalArgumentException("country is required");
        if (movieDto.actors() == null || movieDto.actors().isBlank())
            throw new IllegalArgumentException("actors is required");
        if (movieDto.director() == null || movieDto.director().isBlank())
            throw new IllegalArgumentException("director is required");

        Set<Genre> genres = new HashSet<>();
        movieDto.genres().forEach(genreDto -> {
            genres.add(genreRepository.findById(genreDto.id()).orElse(null));
        });
        Movie movie = new Movie(movieDto.title(), movieDto.duration(), movieDto.avatar(), movieDto.trailer(), movieDto.description(), movieDto.country(), movieDto.ageLimit(), movieDto.premiereDate(), movieDto.rating(), movieDto.actors(), movieDto.director(), movieDto.status(), genres);

        Movie savedMovie = movieRespository.save(movie);
        return transformToDto(savedMovie);
    }

    @Override
    public MovieDto updateMovie(Integer id, MovieDto movieDto) {
        Movie movie = movieRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        if (movieRespository.existsById(id)) {

            if (movieDto.title() != null) movie.setTitle(movieDto.title());

            if (movieDto.duration() != null) movie.setDuration(movieDto.duration());

            if (movieDto.avatar() != null) movie.setAvatar(movieDto.avatar());

            if (movieDto.trailer() != null) movie.setTrailer(movieDto.trailer());

            if (movieDto.description() != null) movie.setDescription(movieDto.description());

            if (movieDto.country() != null) movie.setCountry(movieDto.country());

            if (movieDto.ageLimit() != null) movie.setAgeLimit(movieDto.ageLimit());

            if (movieDto.premiereDate() != null) movie.setPremiereDate(movieDto.premiereDate());

            if (movieDto.rating() != null) movie.setRating(movieDto.rating());

            if (movieDto.actors() != null) movie.setActors(movieDto.actors());

            if (movieDto.director() != null) movie.setDirector(movieDto.director());

            if (movieDto.status() != null) movie.setStatus(movieDto.status());

            if (movieDto.genres() != null) {
                Set<Genre> genres = new HashSet<>();
                movieDto.genres().forEach(genreDto -> {
                    genres.add(genreRepository.findById(genreDto.id()).orElse(null));
                });
                movie.setGenres(genres);
            }
            movieRespository.save(movie);
            return transformToDto(movie);
        }
        return null;
    }

//    @Override
//    public boolean deleteMovie(Long id) {
//        if (movieRespository.existsById(id)) {
//            movieRespository.deleteById(id);
//            return true;
//        }
//        return false;
//    }

    private MovieDto transformToDto(Movie movie) {
        // Avoid returning managed entity collections directly to the DTO. Returning the raw
        // Set<Genre> can trigger lazy loading and potentially mark entities dirty which
        // can cause unexpected flush/commit behavior. For safety return an empty set here.
        Set<GenreDto> genreDtos = new HashSet<>();
        if (movie.getGenres() != null) {
            movie.getGenres().forEach(genre -> genreDtos.add(transformToDto(genre)));
        }
        return new MovieDto(movie.getId(), movie.getTitle(), movie.getDuration(), movie.getAvatar(), movie.getTrailer(), movie.getDescription(), movie.getCountry(), movie.getAgeLimit(), movie.getPremiereDate(), movie.getRating(), movie.getActors(), movie.getDirector(), movie.getStatus(), genreDtos);
    }

    private GenreDto transformToDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName(), genre.getDescription());
    }
}
