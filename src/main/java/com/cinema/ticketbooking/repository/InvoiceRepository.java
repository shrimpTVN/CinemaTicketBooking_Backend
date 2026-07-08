package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    public List<Invoice> findByUserId(Integer userId);
}