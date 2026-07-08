package com.cinema.ticketbooking.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PathsConfig {

    @Bean(name = "publicPaths")
    public List<String> publicPaths() {
        return List.of(
                "/api/movies/**",
                "/api/seat-types/**",
                "/api/halls/**",
                "/api/showtimes/**",
                "/api/genres/**",
                "/api/invoices/**",
                "/api/hall-types/**",
                "/api/price-lists/**",
                "/api/booking/**",
                "/api/products/**",
                "/api/payment-methods/**",
                "/payment-methods/**",
                "/api/showtime-seats/**",

                "/api/auth/**",
                "/api/swagger-ui.html",
                "/swagger-ui/**",
                "/api/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**"
        );
    }

    @Bean(name = "securedPaths")
    public List<String> securedPaths() {
        return List.of(
                "/api/**"
        );
    }


}
