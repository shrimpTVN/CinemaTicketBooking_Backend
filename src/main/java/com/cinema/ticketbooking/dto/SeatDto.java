package com.cinema.ticketbooking.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.Seat}
 */
public record SeatDto(Integer id, @NotNull Integer seatTypeId, @NotNull @Size(max = 5) String rowLabel, @NotNull Integer colNumber,
                      @Size(max = 50) String status) implements Serializable {
}
