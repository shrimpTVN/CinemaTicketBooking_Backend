package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}