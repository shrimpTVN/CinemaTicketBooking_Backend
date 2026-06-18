package com.cinema.ticketbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name="movie")
@Getter @Setter
public class Movie extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    @NotBlank
    @Column(name="title", nullable = false)
    private String title;

    @Size(min=0)
    @Column(name="duration", nullable = false)
    private int duration;

    @Size(max=500)
    @NotBlank
    @Column(name="avatar", nullable = false)
    private String avatar;

    @Size(max=500)
    @NotBlank
    @Column(name="trailer", nullable = false)
    private String trailer;

    @Lob
    @Column(name="description", nullable = false)
    private String description;

    @Size(max=100)
    @NotBlank
    @Column(name="country", nullable = false)
    private String country;

    @Size(min=1, max=100)
    @ColumnDefault("1")
    @Column(name="age-limit", nullable = false)
    private int ageLimit;


    @Column(name="premiere-date", nullable = false)
    private Instant premiereDate;

    @Size(min=0, max=10)
    @Column(name="rating")
    private float rating;

    @Size(max=500)
    @NotBlank
    @Column(name="actors", nullable = false)
    private String actors;

    @Size(max=100)
    @NotBlank
    @Column(name="director", nullable = false)
    private String director;

    @Size(max=500)
    @NotBlank
    @Column(name="genres", nullable = false)
    private String genres;

    @Size(max=50)
    @NotBlank
    @ColumnDefault("COMING SOON")
    @Column(name="status", nullable = false)
    private String status;
}
