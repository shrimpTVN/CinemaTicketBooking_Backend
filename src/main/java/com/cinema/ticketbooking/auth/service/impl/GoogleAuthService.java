package com.cinema.ticketbooking.auth.service.impl;

import com.cinema.ticketbooking.auth.service.IGoogleAuthService;
import com.cinema.ticketbooking.dto.requestDto.UserRequestDto;
import com.cinema.ticketbooking.dto.responseDto.UserResponseDto;
import com.cinema.ticketbooking.entity.User;
import com.cinema.ticketbooking.repository.UserRepository;
import com.cinema.ticketbooking.user.service.IUserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class GoogleAuthService implements IGoogleAuthService {

    private final IUserService userService;
    private final UserRepository userRepository;
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    public GoogleIdToken.Payload verifyToken(String idTokenString) throws GeneralSecurityException, IOException {
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            return idToken.getPayload();
        } else {
            throw new IllegalArgumentException("Invalid ID token.");
        }
    }

    @Override
    public String getGoogleAuthUrl() {
        return "";
    }

    @Override
    public String getAccessToken(String code) {
        return "";
    }

    @Override
    public UserResponseDto getUserInfo(String accessToken) throws GeneralSecurityException, IOException {
        GoogleIdToken.Payload payload = verifyToken(accessToken);
        String email = payload.getEmail();
        String name = payload.get("name").toString();

        if (!userRepository.existsUserByEmail(email)) {
            return userService.register(new UserRequestDto(name, LocalDate.of(2000, 1, 1),
                    0, "0123456789", email, "random-password", 0, "ON"));
        }
        return userService.getUserByEmail(email);
    }
}