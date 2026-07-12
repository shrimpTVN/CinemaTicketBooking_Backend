package com.cinema.ticketbooking.core.websocket;

import com.cinema.ticketbooking.booking.service.IShowtimeSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final IShowtimeSeatService showtimeSeatService;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor= StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();

        Integer userId= (Integer)headerAccessor.getSessionAttributes().get("userId");

        if (userId != null) {
            System.out.println("User disconnected: " + userId + ", session ID: " + sessionId);
            // Release all seats held by this user
            showtimeSeatService.releaseAllSeatsHoldByUser(userId);
        } else {
            System.out.println("User disconnected: session ID: " + sessionId + ", but no userId found in session attributes.");
        }
    }
}
