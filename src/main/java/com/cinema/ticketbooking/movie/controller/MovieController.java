package com.cinema.ticketbooking.movie.controller;

import com.cinema.ticketbooking.dto.MovieDto;
import com.cinema.ticketbooking.movie.service.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Integer id){
        MovieDto movieDto = movieService.getMovieById(id);
        return ResponseEntity.ok().body(movieDto);
    }

    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Integer id, @RequestBody MovieDto movieDto){
        System.out.println(id + "  " +movieDto);
        MovieDto  updatedMovieDto = movieService.updateMovie(id, movieDto);
        return ResponseEntity.ok().body(updatedMovieDto);
    }

    @PostMapping("")
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto){
        MovieDto  createdMovieDto = movieService.createMovie(movieDto);
        return  ResponseEntity.ok().body(createdMovieDto);
    }
}
