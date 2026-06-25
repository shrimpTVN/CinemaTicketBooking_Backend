package com.cinema.ticketbooking;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.User}
 */
public record UserDto(Integer id, @NotNull @Size(max = 100) String name, @NotNull LocalDate doB, @NotNull Integer point,
                      @NotNull @Size(max = 10) String phoneNumber, @NotNull @Size(max = 50) String email,
                      @NotNull @Size(max = 50) String password, @NotNull @Size(max = 20) String role,
                      @Size(max = 50) String status) implements Serializable {
}