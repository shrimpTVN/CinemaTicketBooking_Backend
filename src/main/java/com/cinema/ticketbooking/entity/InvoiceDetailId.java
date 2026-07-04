package com.cinema.ticketbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode // CRITICAL: Required by JPA for composite keys
@Embeddable
public class InvoiceDetailId implements Serializable {
    @Column(name = "invoice_id")
    private Integer invoiceId;

    @Column(name = "product_id")
    private Integer productId;
}