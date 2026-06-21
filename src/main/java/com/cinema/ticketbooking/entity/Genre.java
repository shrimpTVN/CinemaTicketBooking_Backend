package com.cinema.ticketbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="genre")
@Getter
@Setter
public class Genre extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 100)
    @Column(name="name", nullable=false)
    @NotBlank
    private String name;

    @Column(name="description", nullable=false)
    @Lob
    @NotBlank
    private String description;

}
