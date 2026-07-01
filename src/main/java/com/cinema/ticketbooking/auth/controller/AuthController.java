package com.cinema.ticketbooking.auth.controller;

import com.cinema.ticketbooking.core.constant.ApplicationConstants;
import com.cinema.ticketbooking.core.util.JwtUtil;
import com.cinema.ticketbooking.dto.requestDto.LoginRequestDto;
import com.cinema.ticketbooking.dto.requestDto.UserRequestDto;
import com.cinema.ticketbooking.dto.responseDto.LoginResponseDto;
import com.cinema.ticketbooking.dto.responseDto.UserResponseDto;
import com.cinema.ticketbooking.user.service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> apiLogin(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            var resultAuthentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.email(),
                    loginRequestDto.password()));

            String jwtToken = jwtUtil.generateJwtToken(resultAuthentication);

            var fetchedUser = (User) resultAuthentication.getPrincipal();

            UserResponseDto userDto = null;
            if (fetchedUser != null) userDto = userService.getUserByEmail(fetchedUser.getUsername());

            // 1. Build the Secure HttpOnly Cookie
            ResponseCookie jwtCookie = ResponseCookie.from(ApplicationConstants.JWT_COOKIE_NAME, jwtToken)
                    .httpOnly(true)   // JavaScript CANNOT read this
                    .secure(false)    // Set to TRUE in production when using HTTPS!
                    .path("/")        // Cookie is valid for all routes
                    .maxAge(24* 60 * 60)  // 24 hours (must match your JWT expiration)
                    .sameSite("Strict" ) // Prevents the browser from sending this cookie from other websites
                    .build();

            // 2. Return the response with the Set-Cookie header.
            // Notice we pass 'null' for the token in the DTO, keeping it out of the JSON body.
            return ResponseEntity.status(HttpStatus.OK)
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
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

    private ResponseEntity<LoginResponseDto> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new LoginResponseDto(message, null));
    }
}
