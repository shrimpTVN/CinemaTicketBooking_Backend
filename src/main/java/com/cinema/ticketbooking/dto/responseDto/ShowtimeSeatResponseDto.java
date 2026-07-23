package com.cinema.ticketbooking.dto.responseDto;

import jakarta.validation.constraints.NotNull;

public record ShowtimeSeatResponseDto(@NotNull Integer showtimeId, @NotNull Integer seatId, @NotNull Integer holdBy, @NotNull String status) {
}
