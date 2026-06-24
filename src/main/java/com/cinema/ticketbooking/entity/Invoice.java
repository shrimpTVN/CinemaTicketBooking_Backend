package com.cinema.ticketbooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "invoice", schema = "cinema")
public class Invoice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Size(max = 50)
    @NotNull
    @Column(name = "payment_method", nullable = false, length = 50)
    private String paymentMethod;

    @NotNull
    @Column(name = "vat", nullable = false, precision = 5, scale = 2)
    private BigDecimal vat;

    @Size(max = 50)
    @ColumnDefault("'PENDING'")
    @Column(name = "status", length = 50)
    private String status;

    @OneToMany(mappedBy = "invoice")
    private Set<InvoiceDetail> invoiceDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "invoice")
    private Set<Ticket> tickets = new LinkedHashSet<>();


}