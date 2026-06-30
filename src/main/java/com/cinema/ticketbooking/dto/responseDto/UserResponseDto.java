package com.cinema.ticketbooking.dto.responseDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.User}
 */
public record UserResponseDto(Integer id, @NotNull @Size(max = 100) String name, @NotNull LocalDate doB, Integer point,
                              @NotNull @Size(max = 10) String phoneNumber, @NotNull @Size(max = 50) String email,
                              @Size(max = 20) String role,
                              @Size(max = 50) String status) implements Serializable {
}