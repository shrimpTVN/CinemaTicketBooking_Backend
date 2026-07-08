package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.InvoiceDetail;
import com.cinema.ticketbooking.entity.InvoiceDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, InvoiceDetailId> {
}