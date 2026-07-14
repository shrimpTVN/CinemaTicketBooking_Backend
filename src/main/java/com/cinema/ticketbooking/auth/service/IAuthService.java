package com.cinema.ticketbooking.auth.service;

import com.cinema.ticketbooking.dto.requestDto.LoginRequestDto;
import com.cinema.ticketbooking.dto.responseDto.UserResponseDto;
import org.springframework.http.ResponseCookie;

public interface IAuthService {

    public UserResponseDto verifyUser(LoginRequestDto loginRequestDto);
    public ResponseCookie getUserCookie(String jwtToken);
}
