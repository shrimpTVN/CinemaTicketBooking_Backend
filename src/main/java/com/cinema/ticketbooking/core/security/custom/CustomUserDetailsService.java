package com.cinema.ticketbooking.core.security.custom;

import com.cinema.ticketbooking.entity.User;
import com.cinema.ticketbooking.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        // 1. Fetch YOUR user from MySQL
        User mySqlUser = userRepository.findByEmail(email);
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        if (mySqlUser == null) {
            throw new UsernameNotFoundException(email);
        }
        // 2. Translate YOUR user into Spring Security's UserDetails object
        return new CustomUserDetails(
                mySqlUser.getId(),
                mySqlUser.getEmail(),
                mySqlUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + mySqlUser.getRole().getName().toUpperCase()))
        );
    }
}
