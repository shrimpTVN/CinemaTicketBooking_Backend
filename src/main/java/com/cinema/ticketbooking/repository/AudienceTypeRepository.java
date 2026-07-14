package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.AudienceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudienceTypeRepository extends JpaRepository<AudienceType, Integer> {
}