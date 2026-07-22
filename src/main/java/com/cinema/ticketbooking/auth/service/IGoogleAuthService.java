package com.cinema.ticketbooking.auth.service;

import com.cinema.ticketbooking.dto.responseDto.UserResponseDto;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface IGoogleAuthService {
    String getGoogleAuthUrl();
    String getAccessToken(String code);
    UserResponseDto getUserInfo(String accessToken) throws GeneralSecurityException, IOException;
}
