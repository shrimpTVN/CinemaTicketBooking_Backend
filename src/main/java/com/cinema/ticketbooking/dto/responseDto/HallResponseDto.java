package com.cinema.ticketbooking.dto.responseDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.Hall}
 */
public record HallResponseDto(Integer id, @NotNull @Size(max = 100) String name, @NotNull Integer width,
                              @NotNull Integer height, @NotNull String hallType, @Size(max = 50) String status) implements Serializable {
}