package com.cinema.ticketbooking.dto.requestDto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ShowtimeSeatRequestDto(@NotNull List<Integer> seatIds,
                                     @NotNull Integer userId) {
}
