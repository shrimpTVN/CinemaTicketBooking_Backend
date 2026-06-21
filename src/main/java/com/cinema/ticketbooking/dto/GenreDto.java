package com.cinema.ticketbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.io.Serializable;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.Genre}
 */
public record GenreDto(@DefaultValue("0") Long id, @Size(min = 1, max = 100) @NotBlank String name,
                       @NotBlank String description) implements Serializable {
}