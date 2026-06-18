package com.cinema.ticketbooking.movie.controller;

import com.cinema.ticketbooking.dto.MovieDto;
import com.cinema.ticketbooking.movie.service.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final IMovieService movieService;

    @GetMapping({"","/"})
    public ResponseEntity<List<MovieDto>> getAllMovies(){
        List<MovieDto> movieDtoList = movieService.getAllMovies();
        return ResponseEntity.ok().body(movieDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getMovieById(@PathVariable String id){

        return ResponseEntity.ok().body("Get movie by id: " + id);
    }

}
