package com.cinema.ticketbooking.core.exception;

/**
 * Simple exception to represent a missing resource (HTTP 404).
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

