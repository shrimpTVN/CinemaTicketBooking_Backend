package com.cinema.ticketbooking.dto.requestDto;

import com.cinema.ticketbooking.dto.HallDto;
import com.cinema.ticketbooking.dto.MovieDto;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;


public record ShowtimeRequestDto(@NotNull Integer hallId, @NotNull Integer movieId, @NotNull LocalDate date,
                                 @NotNull LocalTime startTime, @NotNull String type) implements Serializable {
}
