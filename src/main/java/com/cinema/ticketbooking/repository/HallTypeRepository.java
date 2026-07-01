package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.HallType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallTypeRepository extends JpaRepository<HallType, Integer> {

    public HallType findByName(String name);
}