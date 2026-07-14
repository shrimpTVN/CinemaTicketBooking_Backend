package com.cinema.ticketbooking.dto.websocket;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ShowtimeSeatUpdateEventDto(@NotNull Integer showtimeId, @NotNull List<Integer> seatIds,
                                         @NotNull String status, @NotNull Integer userId) {
}


