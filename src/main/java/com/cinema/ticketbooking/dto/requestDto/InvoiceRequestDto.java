package com.cinema.ticketbooking.dto.requestDto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record InvoiceRequestDto(@NotNull Integer userId, @NotNull Integer showtimeId,
                                @NotNull List<SeatCheckoutRequestDto> seatCheckouts,
                                @NotNull List<InvoiceDetailRequestDto> invoiceDetails,
                                @NotNull String paymentMethod) {
}
