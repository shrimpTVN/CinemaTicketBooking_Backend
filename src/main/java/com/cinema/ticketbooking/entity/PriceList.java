package com.cinema.ticketbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "price_list", schema = "cinema")
public class PriceList extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hall_type_id", nullable = false)
    private HallType hallType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seat_type_id", nullable = false)
    private SeatType seatType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "audience_type_id", nullable = false)
    private AudienceType audienceType;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Size(max = 20)
    @NotNull
    @Column(name = "days", nullable = false, length = 20)
    private List<String> days;

    @Size(max = 50)
    @ColumnDefault("'ON'")
    @Column(name = "status", length = 50)
    private String status;



}