package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}