package com.cinema.ticketbooking.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.SeatType}
 */
public record SeatTypeDto(Integer id, @NotNull @Size(max = 100) String name, @NotNull BigDecimal priceSurcharge,
                          @NotNull String description, @NotNull @Size(max = 500) String image,
                          @Size(max = 50) String status) implements Serializable {
}