package com.cinema.ticketbooking.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRatingRequestDto(@NotNull Integer movieId,
                                      @NotNull Integer userId,
                                      @NotNull Float rating,
                                      @NotNull @NotBlank String comment) {

}
