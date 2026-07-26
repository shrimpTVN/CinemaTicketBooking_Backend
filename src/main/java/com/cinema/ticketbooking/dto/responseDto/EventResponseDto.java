package com.cinema.ticketbooking.dto.responseDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.Event}
 */
public record EventResponseDto(Integer id,
                               @NotNull @Size(max = 200) String title,
                               @NotNull String description,
                               @NotNull @Size(max = 500) String poster,
                               @NotNull @Size(max = 500) String banner,
                               @Size(max = 50) String status) implements Serializable {
}