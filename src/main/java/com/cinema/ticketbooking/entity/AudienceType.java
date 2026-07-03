package com.cinema.ticketbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "audience_type", schema = "cinema")
public class AudienceType extends BaseEntity {
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

    @Size(max = 50)
    @ColumnDefault("'ON'")
    @Column(name = "status", length = 50)
    private String status;


}