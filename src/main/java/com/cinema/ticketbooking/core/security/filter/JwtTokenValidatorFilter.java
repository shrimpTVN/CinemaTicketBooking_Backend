package com.cinema.ticketbooking.core.security.filter;

import com.cinema.ticketbooking.core.constant.ApplicationConstants;
import com.cinema.ticketbooking.core.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenValidatorFilter extends OncePerRequestFilter {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private final JwtUtil jwtUtil;

    @Qualifier("publicPaths")
    private final List<String> publicPaths;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = jwtUtil.extractToken(request);

        // If we found our token cookie, proceed with cryptographic validation
        if (jwt != null) {
            try {
                // Delegating token parsing entirely to JwtUtil for Single Responsibility Principle
                Claims claims = jwtUtil.parseClaims(jwt);

                String username = String.valueOf(claims.get("username"));
                String roles = String.valueOf(claims.get("roles"));

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username, null, AuthorityUtils.commaSeparatedStringToAuthorityList(roles));

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (ExpiredJwtException exception) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token Expired");
                return;
            } catch (Exception exception) {
                throw new BadCredentialsException("Invalid Token received!");
            }
        }

        filterChain.doFilter(request, response);
    }

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        String path = request.getRequestURI();
//        return publicPaths.stream().anyMatch(publicPath ->
//                pathMatcher.match(publicPath, path));
//    }

}

//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String authHeader = request.getHeader(ApplicationConstants.JWT_HEADER);
//
//        if (null != authHeader) {
//            try {
//                // Extract the JWT token
//                // Whoever bears (holds) the token is trusted and can access the protected resource.
//                String jwt = authHeader.substring(7); // Remove 'Bearer ' prefix
//                Environment env = getEnvironment();
//                if (null != env) {
//                    String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY,
//                            ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
//                    SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//
//                    if (null != secretKey) {
//                        Claims claims = Jwts.parser().verifyWith(secretKey)
//                                .build().parseSignedClaims(jwt).getPayload();
//                        String username = String.valueOf(claims.get("username"));
//                        String roles = String.valueOf(claims.get("roles"));
//                        Authentication authentication = new UsernamePasswordAuthenticationToken(username,
//                                null, AuthorityUtils.commaSeparatedStringToAuthorityList(roles));
//                        SecurityContextHolder.getContext().setAuthentication(authentication);
//                    }
//                }
//
//            } catch (ExpiredJwtException exception) {
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.getWriter().write("Token Expired");
//                return;
//            } catch (Exception exception) {
//                throw new BadCredentialsException("Invalid Token received!");
//            }
//        }
//        filterChain.doFilter(request, response);
//
//    }

