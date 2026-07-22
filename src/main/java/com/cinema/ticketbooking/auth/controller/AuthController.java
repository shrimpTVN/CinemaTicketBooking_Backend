package com.cinema.ticketbooking.auth.controller;

import com.cinema.ticketbooking.auth.service.IAuthService;
import com.cinema.ticketbooking.auth.service.IGoogleAuthService;
import com.cinema.ticketbooking.core.security.custom.CustomUserDetails;
import com.cinema.ticketbooking.core.util.JwtUtil;
import com.cinema.ticketbooking.dto.TokenDto;
import com.cinema.ticketbooking.dto.requestDto.LoginRequestDto;
import com.cinema.ticketbooking.dto.requestDto.UserRequestDto;
import com.cinema.ticketbooking.dto.responseDto.LoginResponseDto;
import com.cinema.ticketbooking.dto.responseDto.UserResponseDto;
import com.cinema.ticketbooking.entity.Role;
import com.cinema.ticketbooking.entity.User;
import com.cinema.ticketbooking.user.service.impl.UserServiceImpl;
import jakarta.validation.Payload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.AuthProvider;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserServiceImpl userService;
    private final IAuthService authService;
    private final IGoogleAuthService googleAuthService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> apiLogin(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            var resultAuthentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.email(),
                    loginRequestDto.password()));

            String jwtToken = jwtUtil.generateJwtToken(resultAuthentication);

            var fetchedUser = (CustomUserDetails) resultAuthentication.getPrincipal();
            UserResponseDto userDto = null;
            if (fetchedUser != null) userDto = userService.getUserByEmail(fetchedUser.getUsername());

            // Return the response with the Set-Cookie header.
            return ResponseEntity.status(HttpStatus.OK)
                    .header(HttpHeaders.SET_COOKIE, authService.getUserCookie(jwtToken).toString())
                    .body(new LoginResponseDto("Login Successful", userDto ));

        } catch (BadCredentialsException ex) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        } catch (AuthenticationException ex) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Authentication failed");
        } catch (Exception e) {
            System.out.println("Error occurred during login: " + e.getMessage());
            return buildErrorResponse(HttpStatus.BAD_REQUEST, "An error occurred while processing your request");
        }

    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto user){
        UserResponseDto newUser = userService.register(user);
        return ResponseEntity.ok(newUser);
    }

    // Inject services via constructor (Clean Architecture standard)
    @PostMapping("/google")
    public ResponseEntity<LoginResponseDto> googleLogin(@RequestBody TokenDto tokenDto) throws GeneralSecurityException, IOException {
        UserResponseDto userDto = googleAuthService.getUserInfo(tokenDto.token());

        // Generate system JWT
        String systemJwt = jwtUtil.generateJwtToken(userDto);
        // Return the response with the Set-Cookie header.
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, authService.getUserCookie(systemJwt).toString())
                .body(new LoginResponseDto("Login Successful", userDto));
    }

    private ResponseEntity<LoginResponseDto> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new LoginResponseDto(message, null));
    }
}
