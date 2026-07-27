package com.cinema.ticketbooking.core.security.filter;

import com.cinema.ticketbooking.core.util.JwtUtil;
import com.cinema.ticketbooking.core.security.custom.CustomUserDetails;
import com.cinema.ticketbooking.core.security.custom.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtTokenValidatorFilter extends OncePerRequestFilter {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Qualifier("publicPaths")
    private final List<String> publicPaths;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        String jwt = jwtUtil.extractToken(request);

        // If we found our token cookie, proceed with cryptographic validation
        if (jwt != null) {
            try {
                // Delegating token parsing entirely to JwtUtil for Single Responsibility Principle
                Claims claims = jwtUtil.parseClaims(jwt);

                // 1. Extract data purely from the token
                String username = String.valueOf(claims.get("username"));
                Integer userId = (Integer) claims.get("userId");
                String authoritiesClaim = String.valueOf(claims.get("roles"));

                // 2. Reconstruct the CustomUserDetails object statelessly
                CustomUserDetails statelessPrincipal = new CustomUserDetails(
                        userId,
                        username,
                        "", // Password is not needed for already-authenticated stateless requests
                        AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim)
                );

                // 3. Set the statelessPrincipal as the FIRST argument (the Principal)
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        statelessPrincipal,
                        null,
                        statelessPrincipal.getAuthorities()
                );

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
