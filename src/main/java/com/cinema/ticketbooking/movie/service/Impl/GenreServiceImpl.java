package com.cinema.ticketbooking.movie.service.Impl;

import com.cinema.ticketbooking.dto.GenreDto;
import com.cinema.ticketbooking.entity.Genre;
import com.cinema.ticketbooking.movie.service.IGenreService;
import com.cinema.ticketbooking.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements IGenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<GenreDto> getAllGenres() {
        List<Genre> genreList = genreRepository.findAll();
        return genreList.stream().map(this::transformToDto).toList();

    }

    @Override
    public GenreDto getGenreById(long id) {
        Genre genre = genreRepository.getReferenceById(id);
        return transformToDto(genre);
    }

    @Override
    public GenreDto createGenre(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setName(genreDto.name());
        genre.setDescription(genreDto.description());
        Genre newGenre = genreRepository.save(genre);
        return transformToDto(newGenre);
    }

    @Override
    public GenreDto updateGenre(Long id, GenreDto genreDto) {
        Genre genre = genreRepository.getReferenceById(id);
        if (genreDto.name() != null) {
            genre.setName(genreDto.name());
        }

        if (genreDto.description() != null) {
            genre.setDescription(genreDto.description());
        }

        Genre updatedGenre = genreRepository.save(genre);
        return transformToDto(updatedGenre);
    }

    @Override
    public boolean deleteGenre(long id) {
        if (genreRepository.existsById(id)) {
            genreRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private GenreDto transformToDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName(), genre.getDescription());
    }
}
