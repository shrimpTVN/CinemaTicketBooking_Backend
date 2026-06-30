package com.cinema.ticketbooking.user.service;

import com.cinema.ticketbooking.dto.requestDto.UserRequestDto;
import com.cinema.ticketbooking.dto.responseDto.UserResponseDto;

import java.util.List;

public interface IUserService {

    public List<UserResponseDto> getAllUsers();
    public UserResponseDto getUserById(int userId);
    public UserResponseDto getUserByEmail(String email);

    public UserResponseDto Login(String email, String password);
    public UserResponseDto register(UserRequestDto userRequestDto);
    public void changePassword(int userId, String email, String oldPassword, String newPassword);

    public UserResponseDto createUser(UserRequestDto userRequestDto);
    public UserResponseDto updateUser(int userId, UserRequestDto userRequestDto);

    public void updateUserStatus(int userId, String status);


}
