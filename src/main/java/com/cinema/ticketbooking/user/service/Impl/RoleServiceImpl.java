package com.cinema.ticketbooking.user.service.Impl;

import com.cinema.ticketbooking.core.exception.custom.ResourceNotFoundException;
import com.cinema.ticketbooking.dto.RoleDto;
import com.cinema.ticketbooking.entity.Role;
import com.cinema.ticketbooking.repository.RoleRepository;
import com.cinema.ticketbooking.user.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles =  roleRepository.findAll();
        return roles.stream().map(this::transformToDto).toList();
    }

    @Override
    public RoleDto getRoleById(Integer id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
        return transformToDto(role);
    }

    private RoleDto transformToDto(Role role) {
        return new RoleDto(role.getId(), role.getName(), role.getDescription(), role.getStatus());
    }
}
