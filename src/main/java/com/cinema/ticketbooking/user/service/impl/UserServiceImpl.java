package com.cinema.ticketbooking.user.service.impl;

import com.cinema.ticketbooking.core.exception.custom.ResourceNotFoundException;
import com.cinema.ticketbooking.dto.requestDto.UserRequestDto;
import com.cinema.ticketbooking.dto.responseDto.UserResponseDto;
import com.cinema.ticketbooking.entity.Role;
import com.cinema.ticketbooking.entity.User;
import com.cinema.ticketbooking.repository.RoleRepository;
import com.cinema.ticketbooking.repository.UserRepository;
import com.cinema.ticketbooking.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDto> getAllUsers() {
//        check role
        List<User> users = userRepository.findAll();
        return users.stream().map(this::transformToDto).toList();
    }

    @Override
    public UserResponseDto getUserById(int userId) {
//        check role or login
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return transformToDto(user);
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }
        return transformToDto(user);
    }

    @Override
    public UserResponseDto Login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }

        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("Password is incorrect");
        }
        return transformToDto(user);
    }

    @Override
    public UserResponseDto register(UserRequestDto userRequestDto) {
        User user = new User();
        if (userRepository.findByEmail(userRequestDto.email()) != null) {
            throw new IllegalArgumentException("Email is already in use");
        }

        user.setName(userRequestDto.name());
        user.setDoB(userRequestDto.doB());
        user.setPhoneNumber(userRequestDto.phoneNumber());
        user.setEmail(userRequestDto.email());

        user.setPassword(passwordEncoder.encode(userRequestDto.password()));
        Role role = roleRepository.findById(2).orElseThrow(() -> new ResourceNotFoundException("Role not found with id: 2"));
        user.setRole(role);
        userRepository.save(user);
        return transformToDto(user);
    }

    @Override
    public void changePassword(int userId, String email, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        if (!user.getEmail().equals(email)) {
            throw new IllegalArgumentException("Email does not match");
        }
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }


    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
//        check role
        User user = new User();
        Role role = roleRepository.findById(userRequestDto.roleId()).orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + userRequestDto.roleId()));
        user.setRole(role);

        user.setName(userRequestDto.name());
        user.setDoB(userRequestDto.doB());
        user.setPhoneNumber(userRequestDto.phoneNumber());
        user.setEmail(userRequestDto.email());
        user.setPassword(passwordEncoder.encode(userRequestDto.password()));

        userRepository.save(user);
        return transformToDto(user);
    }

    @Override
    public UserResponseDto updateUser(int userId, UserRequestDto userRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        if (userRequestDto.name() != null) user.setName(userRequestDto.name());
        if (userRequestDto.doB() != null) user.setDoB(userRequestDto.doB());
        if (userRequestDto.phoneNumber() != null) user.setPhoneNumber(userRequestDto.phoneNumber());
        User updatedUser = userRepository.save(user);
        return transformToDto(updatedUser);
    }

    @Override
    public void updateUserStatus(int userId, String status) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        user.setStatus(status);
        userRepository.save(user);
    }

    public UserResponseDto transformToDto(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getDoB(), user.getPoint(),
                user.getPhoneNumber(), user.getEmail(), user.getRole().getName(), user.getStatus());
    }
}
