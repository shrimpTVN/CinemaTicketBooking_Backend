package com.cinema.ticketbooking.auth.service.impl;

import com.cinema.ticketbooking.auth.service.IAuthService;
import com.cinema.ticketbooking.core.constant.ApplicationConstants;
import com.cinema.ticketbooking.core.util.JwtUtil;
import com.cinema.ticketbooking.dto.requestDto.LoginRequestDto;
import com.cinema.ticketbooking.dto.responseDto.UserResponseDto;
import com.cinema.ticketbooking.user.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserServiceImpl userService;

    @Override
    public UserResponseDto verifyUser(LoginRequestDto loginRequestDto) {


        return null;
    }

    @Override
    public ResponseCookie getUserCookie(String jwtToken) {
        // Build the Secure HttpOnly Cookie
        return ResponseCookie.from(ApplicationConstants.JWT_COOKIE_NAME, jwtToken)
                .httpOnly(true)   // JavaScript CANNOT read this
                .secure(false)    // Set to TRUE in production when using HTTPS!
                .path("/")        // Cookie is valid for all routes
                .maxAge(24* 60 * 60)  // 24 hours (must match your JWT expiration)
//                .sameSite("Strict" ) // Prevents the browser from sending this cookie from other websites
                .build();
    }
}
