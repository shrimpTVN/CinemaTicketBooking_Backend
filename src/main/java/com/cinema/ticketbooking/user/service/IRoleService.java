package com.cinema.ticketbooking.user.service;

import com.cinema.ticketbooking.dto.RoleDto;

import java.util.List;

public interface IRoleService {
    public List<RoleDto> getAllRoles();
    public RoleDto getRoleById(Integer id);
}
