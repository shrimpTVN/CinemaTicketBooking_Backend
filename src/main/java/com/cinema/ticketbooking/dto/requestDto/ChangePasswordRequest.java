package com.cinema.ticketbooking.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(@NotBlank @Email String email, @NotBlank @Size(min = 6) String oldPassword,
                                    @NotBlank @Size(min = 6) String newPassword) {
}
