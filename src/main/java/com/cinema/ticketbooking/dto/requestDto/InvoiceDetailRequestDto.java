package com.cinema.ticketbooking.dto.requestDto;

import jakarta.validation.constraints.NotNull;

public record InvoiceDetailRequestDto(@NotNull Integer productId, @NotNull Integer quantity) {
}
