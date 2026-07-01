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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @NotNull
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "images", nullable = false)
    private List<String> images;

    @Size(max = 50)
    @ColumnDefault("'ON'")
    @Column(name = "status", length = 50)
    private String status ="ON";

    @OneToMany(mappedBy = "hallType")
    private Set<Hall> halls = new LinkedHashSet<>();


}