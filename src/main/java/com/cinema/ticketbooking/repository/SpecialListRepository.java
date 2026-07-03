package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.SpecialList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface SpecialListRepository extends JpaRepository<SpecialList, Integer> {
    public SpecialList findByCode(String code);
}