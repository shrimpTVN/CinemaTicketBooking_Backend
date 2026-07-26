package com.cinema.ticketbooking.core.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class BookingReferenceGenerator {

    private final SecureRandom secureRandom = new SecureRandom();

    // Omitted ambiguous characters (0, O, 1, I, L)
    private static final String ALPHABET = "23456789ABCDEFGHJKMNPQRSTUVWXYZ";

    /**
     * Generates a collision-resistant, human-readable code.
     * Use Case: SMS OTPs for phone verification, or Ticket Booking IDs.
     */
    public String generateHumanReadableCode(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(index));
        }
        return sb.toString();
    }


}