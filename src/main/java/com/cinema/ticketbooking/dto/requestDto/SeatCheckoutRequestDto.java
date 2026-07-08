package com.cinema.ticketbooking.dto.requestDto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SeatCheckoutRequestDto(@NotNull Integer audienceTypeId, @NotNull List<Integer> seatIds) {
}
