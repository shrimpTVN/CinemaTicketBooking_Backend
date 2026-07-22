package com.cinema.ticketbooking.dto.responseDto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.Showtime}
 */
public record ShowtimeResponseDto(Integer id, @NotNull Integer hallId, @NotNull String hallName,
                                  @NotNull Integer movieId,
                                  @NotNull String movieName, @NotNull LocalDate date,
                                  @NotNull LocalTime startTime, @NotNull String type) implements Serializable {
}