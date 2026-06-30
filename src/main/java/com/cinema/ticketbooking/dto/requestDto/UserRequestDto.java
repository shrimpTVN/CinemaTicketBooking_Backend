package com.cinema.ticketbooking.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

public record UserRequestDto(@NotNull @Size(max = 100) String name, @NotNull LocalDate doB, Integer point,
                             @NotNull @Size(max = 10) String phoneNumber, @NotNull @Size(max = 255) String email,
                             @NotNull @Size(max = 255) String password, Integer roleId,
                             @Size(max = 50) String status) implements Serializable {
}