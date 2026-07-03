package com.cinema.ticketbooking.dto.responseDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.PriceList}
 */
public record PriceListResponseDto(Integer id, @NotNull String hallType, @NotNull String seatType,
                                   @NotNull @Size(max = 100) String audienceType,
                                   @NotNull @Size(max = 100) String name,
                                   @NotNull BigDecimal price,
                                   @NotNull List<String> days, @Size(max = 50) String status) implements Serializable {
}