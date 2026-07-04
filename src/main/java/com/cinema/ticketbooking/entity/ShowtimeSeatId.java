package com.cinema.ticketbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode // CRITICAL: Required by JPA for composite keys
@Embeddable
public class ShowtimeSeatId implements Serializable {
    @Column(name = "showtime_id")
    private Integer showtimeId;

    @Column(name = "seat_id")
    private Integer seatId;
}
