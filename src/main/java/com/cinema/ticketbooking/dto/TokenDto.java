package com.cinema.ticketbooking.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenDto(@NotBlank(message = "Google ID token must not be blank") String token) {
}
