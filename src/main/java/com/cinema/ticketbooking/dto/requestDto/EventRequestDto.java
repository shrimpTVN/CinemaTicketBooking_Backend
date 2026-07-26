package com.cinema.ticketbooking.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.Event}
 */
public record EventRequestDto(@NotNull @Size(max = 200) @NotBlank String title,
                              @NotNull @NotBlank String description,
                              @NotNull @Size(max = 500) @NotEmpty String poster,
                              @NotNull @Size(max = 500) @NotEmpty String banner) implements Serializable {
}