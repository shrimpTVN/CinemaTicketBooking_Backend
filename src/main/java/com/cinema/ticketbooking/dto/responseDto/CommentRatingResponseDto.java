package com.cinema.ticketbooking.dto.responseDto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.cinema.ticketbooking.entity.CommentRating}
 */
public record CommentRatingResponseDto(@NotNull Integer id,
                                       @NotNull Integer userId,
                                       @NotNull String userName,
                                       @NotNull Integer movieId,
                                       @NotNull String rating,
                                       @NotNull Float comment,
                                       Instant createdAt,
                                       Instant updatedAt) implements Serializable {
}