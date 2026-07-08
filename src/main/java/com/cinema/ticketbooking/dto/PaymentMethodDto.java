package com.cinema.ticketbooking.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.PaymentMethod}
 */
public record PaymentMethodDto(Integer id, @NotNull @Size(max = 50) String code, @NotNull @Size(max = 100) String name,
                               @NotNull String description, @NotNull @Size(max = 500) String logo, BigDecimal surcharge,
                               @Size(max = 50) String status) implements Serializable {
}