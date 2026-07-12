package com.cinema.ticketbooking.core.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // /topic for public broadcasts (e.g., Hall 1 inventory)
        // /queue for private, user-specific messages (e.g., My Ticket)
        config.enableSimpleBroker("/topic", "/queue");

        //prefix for inbound messages from the clients
        config.setApplicationDestinationPrefixes("/app");

        // Spring uses this prefix to route messages to specific users
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-cinema").setAllowedOriginPatterns("*").withSockJS();
    }
}