package com.cinema.ticketbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "hall_type", schema = "cinema")
public class HallType  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Lob
    @Column(name = "convenience", nullable = false)
    private String convenience;

    @Size(max = 100)
    @NotNull
    @Column(name = "style", nullable = false, length = 100)
    private String style;

    // This creates a separate table: hall_type_images (hall_type_id, image_url)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "hall_type_image", joinColumns = @JoinColumn(name = "hall_type_id"))
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    @Size(max = 50)
    @ColumnDefault("'ON'")
    @Column(name = "status", length = 50)
    private String status ="ON";

    @OneToMany(mappedBy = "hallType")
    private Set<Hall> halls = new LinkedHashSet<>();


}