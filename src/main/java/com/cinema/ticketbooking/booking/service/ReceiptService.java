package com.cinema.ticketbooking.booking.service;

import com.cinema.ticketbooking.dto.ReceiptPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceiptService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void generateAndSendReceipt(Integer userId, Integer invoiceId){
        ReceiptPayload response = new ReceiptPayload(invoiceId, "Your receipt for invoice " + invoiceId);
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(userId), "/queue/receipt", response);
    }

}
