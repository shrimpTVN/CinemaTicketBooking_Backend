package com.cinema.ticketbooking.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.Product}
 */
public record ProductDto(Integer id, @NotNull @Size(max = 100) String name, @NotNull String description,
                         @NotNull BigDecimal price, @NotNull @Size(max = 500) String image,
                         @Size(max = 50) String status) implements Serializable {
}