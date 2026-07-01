package com.cinema.ticketbooking.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record HallRequestDto(@NotNull @Size(max = 100) String name, @NotNull Integer width,
                             @NotNull Integer height, @NotNull int hallTypeId) implements Serializable {
}