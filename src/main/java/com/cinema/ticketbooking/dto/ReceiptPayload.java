package com.cinema.ticketbooking.dto;

import jakarta.validation.constraints.NotNull;

public record ReceiptPayload(@NotNull Integer invoiceId, @NotNull String message) {
}
