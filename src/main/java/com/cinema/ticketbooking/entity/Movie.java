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

    public Movie(@Size(max = 100) @NotBlank String title, Integer duration, @Size(max = 500) @NotBlank String avatar,
                 @Size(max = 500) @NotBlank String trailer, String description, @Size(max = 100) @NotBlank String country,
                 Integer ageLimit, Instant premiereDate, Float rating, @Size(max = 500) @NotBlank String actors,
                 @Size(max = 100) @NotBlank String director, @Size(max = 50) @NotBlank String status, Set<Genre> genres) {
        // Initialize fields from constructor parameters. Use sensible defaults to avoid DB constraint violations
        // when callers provide null for wrapper types.
        this.title = title;
        // duration must be > 0 according to DB constraint; fall back to 1 if null or <= 0
        this.duration = (duration == null || duration <= 0) ? 1 : duration;
        this.avatar = avatar;
        this.trailer = trailer;
        this.description = description;
        this.country = country;
        // ageLimit default to 0 if null (schema allows 0 and has check age_limit >= 0)
        this.ageLimit = (ageLimit == null) ? 1 : ageLimit;
        this.premiereDate = premiereDate;
        // rating constrained between 0 and 10; default to 0 if null or out of range
        float r = (rating == null) ? 0f : rating;
        this.rating = Math.min(10f, Math.max(0f, r));
        this.actors = actors;
        this.director = director;
        this.status = status == null ? "COMING SOON" : status;
        if (genres != null) {
            this.genres = genres;
        }
    }

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



    /**
     * Helper method to maintain bidirectional synchronization.
     * Crucial for maintaining data integrity in memory before flushing to DB.
     */
    public void addGenre(Genre genre) {
        this.genres.add(genre);
        genre.getMovies().add(this);
    }

    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
        genre.getMovies().remove(this);
    }
}
