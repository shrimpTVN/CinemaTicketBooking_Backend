package com.cinema.ticketbooking.dto;

import com.cinema.ticketbooking.entity.Movie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Movie}
 */
public record MovieDto(Long id, @Size(max = 100) @NotBlank String title, int duration,
                       @Size(max = 500) @NotBlank String avatar, @Size(max = 500) @NotBlank String trailer,
                       String description, @Size(max = 100) @NotBlank String country, int ageLimit,
                       Instant premiereDate, float rating, @Size(max = 500) @NotBlank String actors,
                       @Size(max = 100) @NotBlank String director, @Size(max = 500) @NotBlank String genres,
                       @Size(max = 50) @NotBlank String status) implements Serializable {
}