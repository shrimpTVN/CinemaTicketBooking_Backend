package com.cinema.ticketbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "hall", schema = "cinema")
public class Hall extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @Column(name = "width", nullable = false)
    private Integer width;

    @NotNull
    @Column(name = "height", nullable = false)
    private Integer height;

    @Size(max = 50)
    @ColumnDefault("'ON'")
    @Column(name = "status", length = 50)
    private String status="ON";

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hall_type_id", nullable = false)
    private HallType hallType;

    @OneToMany
    @JoinColumn(name = "hall_id")
    private Set<Seat> seats = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "hall_id")
    private Set<Showtime> showtimes = new LinkedHashSet<>();


}