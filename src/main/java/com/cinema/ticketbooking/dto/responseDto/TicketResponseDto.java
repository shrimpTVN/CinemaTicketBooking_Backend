package com.cinema.ticketbooking.dto.responseDto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TicketResponseDto(@NotNull Integer id, @NotNull Integer showtimeId,
                                @NotNull String audienceType, @NotNull String seatRowLabel,
                                @NotNull Integer seatColNumber,
                                @NotNull BigDecimal price) {
}
