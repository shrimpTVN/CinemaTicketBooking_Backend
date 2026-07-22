package com.cinema.ticketbooking.dto;

import com.cinema.ticketbooking.entity.Movie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

/**
 * DTO for {@link Movie}
 */
public record MovieDto(Integer id, @Size(max = 100) @NotBlank String title, Integer duration,
                       @Size(max = 500) @NotBlank String avatar, @Size(max = 500) @NotBlank String trailer,
                       String description, @Size(max = 100) @NotBlank String country, Integer ageLimit,
                       Instant premiereDate, Float rating, @Size(max = 500) @NotBlank String actors,
                       @Size(max = 100) @NotBlank String director,
                       @Size(max = 50) @NotBlank String status, Set<GenreDto> genres) implements Serializable {
}