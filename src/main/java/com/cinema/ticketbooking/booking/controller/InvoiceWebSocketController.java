package com.cinema.ticketbooking.booking.controller;

import com.cinema.ticketbooking.booking.service.ReceiptService;
import com.cinema.ticketbooking.core.security.custom.CustomUserDetails;
import com.cinema.ticketbooking.dto.ReceiptPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class InvoiceWebSocketController {
    private final ReceiptService receiptService;

    @MessageMapping("/invoice/receipt")
    public void handleReceiptRequest(@Payload ReceiptPayload receiptPayload, Principal principal){
        // Cast the generic Principal to Spring's Authentication token
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) principal;

        // Cast the object inside the token to your specific CustomUserDetails class
        CustomUserDetails userDetails = (CustomUserDetails) authToken.getPrincipal();

        // Extract the integer directly from memory!
        assert userDetails != null;
        Integer userId = userDetails.getUserId();

        receiptService.generateAndSendReceipt(userId, receiptPayload.invoiceId());
    }

}
