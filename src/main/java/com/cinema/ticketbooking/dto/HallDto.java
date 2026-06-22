package com.cinema.ticketbooking.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.Hall}
 */
public record HallDto(Integer id, @NotNull @Size(max = 100) String name, @NotNull Integer width,
                      @NotNull Integer height, @NotNull List<String> images, @NotNull String description,
                      @NotNull String convenience, @Size(max = 50) String status) implements Serializable {
}