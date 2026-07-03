package com.cinema.ticketbooking;

import com.cinema.ticketbooking.entity.AudienceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudienceTypeRepository extends JpaRepository<AudienceType, Integer> {
}