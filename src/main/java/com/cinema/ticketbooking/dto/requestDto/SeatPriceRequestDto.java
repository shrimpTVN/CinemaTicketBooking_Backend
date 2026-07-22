package com.cinema.ticketbooking.dto.requestDto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SeatPriceRequestDto(@NotNull Integer seatId, @NotNull Integer audienceTypeId, @NotNull LocalDate date) {
}
