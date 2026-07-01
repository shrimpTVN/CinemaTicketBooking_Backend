package com.cinema.ticketbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ShowtimeSeatId implements Serializable {
    private static final long serialVersionUID = -7654842727464389194L;
    @NotNull
    @Column(name = "showtime_id", nullable = false)
    private Integer showtimeId;

    @NotNull
    @Column(name = "seat_id", nullable = false)
    private Integer seatId;


}