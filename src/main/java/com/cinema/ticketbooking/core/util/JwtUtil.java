package com.cinema.ticketbooking.core.util;

import com.cinema.ticketbooking.core.constant.ApplicationConstants;
import com.cinema.ticketbooking.core.security.custom.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

import static org.springframework.boot.logging.log4j2.Log4J2LoggingSystem.getEnvironment;

@Component
public class JwtUtil {

    // Clean Architecture: Inject configuration directly.
    @Value("${" + ApplicationConstants.JWT_SECRET_KEY + ":" + ApplicationConstants.JWT_SECRET_DEFAULT_VALUE + "}")
    private String secret;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String extractToken(HttpServletRequest request) {
        if (request.getCookies() == null) return null;
        for (Cookie cookie : request.getCookies()) {
            if (ApplicationConstants.JWT_COOKIE_NAME.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
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
