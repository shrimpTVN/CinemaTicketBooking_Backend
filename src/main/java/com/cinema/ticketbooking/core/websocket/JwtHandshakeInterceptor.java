package com.cinema.ticketbooking.core.websocket;

import com.cinema.ticketbooking.core.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtHandshakeInterceptor implements HandshakeInterceptor {
    private final JwtUtil jwtTokenProvider;
    @Override
    public boolean beforeHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response,
                                   @NonNull WebSocketHandler wsHandler, @NonNull Map<String, Object> attributes) throws Exception {


        // Ensure the request is a standard servlet request so we can access HTTP features
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            String token = jwtTokenProvider.extractToken(servletRequest);

            // 1. Validate the token using your existing security utility
            if (jwtTokenProvider.validateToken(token)) {

                // 2. Extract the user ID (adjust method name based on your JWT class)
                Integer userId = jwtTokenProvider.getUserIdFromToken(token);

                // 3. SECURE STATE TRANSFER: Save the ID into the WebSocket attributes
                // This exact map is what the SessionDisconnectEvent reads later!
                attributes.put("userId", userId);

                System.out.println("Handshake successful. Upgrading connection for User ID: " + userId);
                return true; // Allow the connection to proceed
            }
        }
          // Architectural Choice: Reject the connection entirely if they don't have a valid JWT.
          // Return false to abort the HTTP 101 Upgrade, throwing an error to the React client.
        System.out.println("Handshake failed: No valid cinema_jwt cookie found.");
        return false;
}

    /**
     * Invoked after the handshake is done. The response status and headers indicate
     * the results of the handshake, i.e. whether it was successful or not.
     */
    @Override
    public void afterHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response,
                               @NonNull WebSocketHandler wsHandler, @Nullable Exception exception) {

    }
}