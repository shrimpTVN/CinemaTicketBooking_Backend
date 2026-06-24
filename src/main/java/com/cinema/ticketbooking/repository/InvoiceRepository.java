package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}