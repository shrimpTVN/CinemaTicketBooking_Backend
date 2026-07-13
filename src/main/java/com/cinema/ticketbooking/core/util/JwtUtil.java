package com.cinema.ticketbooking.core.util;

import com.cinema.ticketbooking.core.constant.ApplicationConstants;
import com.cinema.ticketbooking.core.security.custom.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtUtil  {
    // 1. Clean Architecture: Let Spring inject the Environment
    private final Environment env;

    private SecretKey getSecretKey() {

        String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY,
                ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
//        System.out.println("Generating SecretKey for JWT signing and validation..." + secret);
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String extractToken(HttpServletRequest request) {
//        System.out.println("Extracting JWT token from request cookies..." + request.getCookies());
        if (request.getCookies() == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if (ApplicationConstants.JWT_COOKIE_NAME.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
//        System.out.println("No JWT cookie found in the request.");
        return null;
    }

    public Boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims parseClaims(String token) {
//        System.out.println("Parsing JWT token claims..." + token);
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Integer getUserIdFromToken(String token) {
        return parseClaims(token).get("userId", Integer.class);
    }

    public String generateJwtToken(Authentication authentication) {
        CustomUserDetails fetchedUser = (CustomUserDetails) authentication.getPrincipal();
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        if (fetchedUser != null) {
            return Jwts.builder()
                    .issuer("ticket-booking")
                    .subject("JWT Token")
                    .claim("username", fetchedUser.getUsername())
                    .claim("userId", fetchedUser.getUserId())
                    .claim("roles", roles)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                    .signWith(getSecretKey())
                    .compact();
        }
        throw new IllegalArgumentException("Invalid authentication information");
    }
}