package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceListRepository extends JpaRepository<PriceList, Integer> {
}