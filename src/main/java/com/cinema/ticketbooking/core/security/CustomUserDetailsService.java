package com.cinema.ticketbooking.core.security;

import com.cinema.ticketbooking.entity.User;
import com.cinema.ticketbooking.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    // Inject your actual database repository here
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Fetch YOUR user from MySQL
        User mySqlUser = userRepository.findByEmail(email);
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        if (mySqlUser == null) {
            throw new UsernameNotFoundException(email);
        }
        // 2. Translate YOUR user into Spring Security's UserDetails object
        return org.springframework.security.core.userdetails.User.builder()
                .username(mySqlUser.getEmail())
                .password(mySqlUser.getPassword()) // This is the BCrypt hash we made earlier!
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + mySqlUser.getRole().getName())))
                .build();
    }
}
