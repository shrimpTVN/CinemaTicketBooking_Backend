package com.cinema.ticketbooking;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.HallType}
 */
public record HallTypeDto(Integer id, @NotNull @Size(max = 100) String name, @NotNull String description,
                          @NotNull String convenience, @NotNull @Size(max = 100) String style,
                          @NotNull List<List<String>> images, @Size(max = 50) String status) implements Serializable {
}