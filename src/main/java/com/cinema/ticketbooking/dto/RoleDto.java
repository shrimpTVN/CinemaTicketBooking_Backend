package com.cinema.ticketbooking.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.Role}
 */
public record RoleDto(Integer id, @NotNull @Size(max = 100) String name, @NotNull String description,
                      @Size(max = 50) String status) implements Serializable {
}