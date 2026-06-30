package com.cinema.ticketbooking.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record LoginRequestDto(@NotNull @Email String email, @NotNull @Size(max=50, min=6) String password) {
}
