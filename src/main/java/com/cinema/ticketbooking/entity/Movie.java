package com.cinema.ticketbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="movie")
@Getter @Setter
@NoArgsConstructor
public class Movie extends BaseEntity {
    @OneToMany(mappedBy = "movie", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Showtime> showtimes = new LinkedHashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 100)
    @NotBlank
    @Column(name="title", nullable = false)
    private String title;

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

    @Column(name="age_limit", nullable = false)
    private int ageLimit;


    @Column(name="premiere_date", nullable = false)
    private Instant premiereDate;


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

    @Size(max=50)
    @ColumnDefault("COMING SOON")
    @Column(name="status", nullable = false)
    private String status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();
}
