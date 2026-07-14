package com.cinema.ticketbooking.core.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

public class CustomHandshakeHandler  extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        // Extract the userId from the request parameters or headers
        String userId = request.getHeaders().getFirst("userId");
        if (userId == null) {
            userId = request.getURI().getQuery(); // Fallback to query parameters if needed
        }

        // You can add more logic here to validate the userId or fetch user details from a database

        return new StompPrincipal(userId);
    }
}
