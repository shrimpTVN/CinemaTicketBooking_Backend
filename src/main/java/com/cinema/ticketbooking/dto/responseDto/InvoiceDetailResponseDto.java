package com.cinema.ticketbooking.dto.responseDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record InvoiceDetailResponseDto(@NotNull String productName, @Min(0) @NotNull Integer quantity) {
}
