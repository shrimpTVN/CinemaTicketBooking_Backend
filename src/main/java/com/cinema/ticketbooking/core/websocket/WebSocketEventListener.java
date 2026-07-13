package com.cinema.ticketbooking.core.websocket;

import com.cinema.ticketbooking.booking.service.IShowtimeSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final IShowtimeSeatService showtimeSeatService;

    // ARCHITECTURAL ADDITION: Spring's built-in active user tracker
    private final SimpUserRegistry simpUserRegistry;

    // ARCHITECTURAL ADDITION: Detect when a user initiates a connection
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        // Retrieve the user ID established during the HandshakeInterceptor phase
        Integer userId = (Integer) headerAccessor.getSessionAttributes().get("userId");
        String sessionId = headerAccessor.getSessionId();

        if (userId != null) {
            System.out.println("⚡ NEW CONNECTION REQUEST: User " + userId + " is connecting. (Session ID: " + sessionId + ")");

            // Note: simpUserRegistry updates a few milliseconds AFTER this event completes,
            // but you can monitor the total load trend here.
            System.out.println("📊 Current Active WebSocket Users: " + simpUserRegistry.getUserCount());
        } else {
            System.out.println("⚠️ Anonymous connection request received (Session ID: " + sessionId + ")");
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor= StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();

        Integer userId= (Integer)headerAccessor.getSessionAttributes().get("userId");

        if (userId != null) {
            System.out.println("User disconnected: " + userId + ", session ID: " + sessionId);
            System.out.println("📉 Connection dropped. Remaining Active Users: " + (simpUserRegistry.getUserCount() ));
            // Release all seats held by this user
            showtimeSeatService.releaseAllSeatsHoldByUser(userId);
        } else {
            System.out.println("User disconnected: session ID: " + sessionId + ", but no userId found in session attributes.");
        }
    }
}
