package com.cinema.ticketbooking.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.PriceList}
 */
public record PriceListRequestDto(@NotNull Integer hallTypeId, @NotNull Integer seatTypeId,
                                  @NotNull @Size(max = 100) Integer audienceTypeId,
                                  @NotNull @Size(max = 100) String name,
                                  @NotNull BigDecimal price,
                                  @NotNull List<String> days) implements Serializable {
}