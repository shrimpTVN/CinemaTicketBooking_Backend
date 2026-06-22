package com.cinema.ticketbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "seat_type", schema = "cinema")
@NoArgsConstructor
public class SeatType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @ColumnDefault("0.00")
    @DecimalMin(value = "0.0", message = "Giá tiền phải lớn hơn hoặc bằng 0")
    @Column(name = "price_surcharge", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceSurcharge;

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Size(max = 500)
    @NotNull
    @Column(name = "image", nullable = false, length = 500)
    private String image;

    @Size(max=50)
    @NotNull
    @ColumnDefault("'ON'")
    private String status;

}