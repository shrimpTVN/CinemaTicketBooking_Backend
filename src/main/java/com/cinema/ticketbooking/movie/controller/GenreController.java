package com.cinema.ticketbooking.movie.controller;

import com.cinema.ticketbooking.dto.GenreDto;
import com.cinema.ticketbooking.movie.service.IGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/genres")
@RequiredArgsConstructor
public class GenreController {

    private final IGenreService genreService;

    @GetMapping(path="/public")
    public ResponseEntity<List<GenreDto>> getAllGenres(){
        return ResponseEntity.ok().body(genreService.getAllGenres());
    }

    @GetMapping(path="/public/{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable Long id){
        return ResponseEntity.ok().body(genreService.getGenreById(id));
    }

    @PostMapping(path={"","/"})
    public ResponseEntity<GenreDto> createGenre(@RequestBody GenreDto genreDto){
        return ResponseEntity.ok().body(genreService.createGenre(genreDto));
    }

    @PatchMapping(path="/{id}")
    @PutMapping(path="/{id}")
    public ResponseEntity<GenreDto> updateGenre(@PathVariable Long id, @RequestBody GenreDto genreDto){

        return ResponseEntity.ok().body(genreService.updateGenre(id, genreDto));
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable Long id){
        genreService.deleteGenre(id);
        return ResponseEntity.ok("Deleted genre successfully");
    }
}
