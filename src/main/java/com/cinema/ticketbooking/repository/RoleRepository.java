package com.cinema.ticketbooking.repository;

import com.cinema.ticketbooking.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}