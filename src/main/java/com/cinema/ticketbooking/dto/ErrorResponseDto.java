package com.cinema.ticketbooking.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDto(String apiPath, HttpStatus httpStatus,
                               String errorMessage, LocalDateTime errorTime) {
}
