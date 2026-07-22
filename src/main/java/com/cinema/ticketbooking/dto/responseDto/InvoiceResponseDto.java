package com.cinema.ticketbooking.dto.responseDto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record InvoiceResponseDto(@NotNull Integer invoiceId, @NotNull Integer userId,
                                 @NotNull ShowtimeResponseDto showtime,
                                 @NotNull List<TicketResponseDto> tickets,
                                 @NotNull List<InvoiceDetailResponseDto> products,
                                 @NotNull String paymentMethod, @NotNull BigDecimal totalAmount,
                                 @NotNull BigDecimal vat) {

}
