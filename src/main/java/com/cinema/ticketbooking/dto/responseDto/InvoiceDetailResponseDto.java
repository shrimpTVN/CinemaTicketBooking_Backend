package com.cinema.ticketbooking.dto.responseDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record InvoiceDetailResponseDto(@NotNull String productName, @Min(0) @NotNull Integer quantity, @NotNull BigDecimal price) {
}
