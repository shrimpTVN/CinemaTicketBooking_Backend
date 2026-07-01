package com.cinema.ticketbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "showtime_seat", schema = "cinema")
public class ShowtimeSeat extends BaseEntity {
    @EmbeddedId
    private ShowtimeSeatId id;

    @MapsId("showtimeId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;

    @MapsId("seatId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @Size(max = 50)
    @ColumnDefault("'AVAILABLE'")
    @Column(name = "status", length = 50)
    private String status;

    @ColumnDefault("0")
    @Column(name = "holdBy")
    private Integer holdBy;

    @Column(name = "holdUntil")
    private Instant holdUntil;


}