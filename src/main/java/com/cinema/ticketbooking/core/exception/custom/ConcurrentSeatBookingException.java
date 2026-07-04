package com.cinema.ticketbooking.core.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Thrown when two or more users attempt to book or hold the exact same seating inventory
 * simultaneously, resulting in an Optimistic Locking collision.
 */
@ResponseStatus(HttpStatus.CONFLICT) // Automatically maps to HTTP 409 if not caught by advice
public class ConcurrentSeatBookingException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ConcurrentSeatBookingException(String message) {
        super(message);
    }

    public ConcurrentSeatBookingException(String message, Throwable cause) {
        super(message, cause);
    }
}