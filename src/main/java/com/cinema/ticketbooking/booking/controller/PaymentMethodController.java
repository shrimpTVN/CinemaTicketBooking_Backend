package com.cinema.ticketbooking.booking.controller;

import com.cinema.ticketbooking.booking.service.IPaymentMethodService;
import com.cinema.ticketbooking.dto.PaymentMethodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment-methods")
@RequiredArgsConstructor
public class PaymentMethodController {
    private final IPaymentMethodService paymentMethodService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<PaymentMethodDto>> getAllPaymentMethods() {
        return ResponseEntity.ok(paymentMethodService.getAllPaymentMethods());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDto> getPaymentMethodById(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentMethodService.getPaymentMethodById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"", "/"})
    public ResponseEntity<PaymentMethodDto> createPaymentMethod(@RequestBody PaymentMethodDto paymentMethodDto) {
        return ResponseEntity.ok(paymentMethodService.createPaymentMethod(paymentMethodDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodDto> updatePaymentMethod(@PathVariable Integer id, @RequestBody PaymentMethodDto paymentMethodDto) {
        return ResponseEntity.ok(paymentMethodService.updatePaymentMethod(id, paymentMethodDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaymentMethod(@PathVariable Integer id) {
        paymentMethodService.deletePaymentMethod(id);
        return ResponseEntity.ok("Deleted payment method successfully");
    }
}
