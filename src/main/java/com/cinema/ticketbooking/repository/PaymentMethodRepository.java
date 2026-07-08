package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
    PaymentMethod findByCode(String code);

    PaymentMethod findByName(String name);
}