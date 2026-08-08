package com.cinema.ticketbooking.booking.controller;

import com.cinema.ticketbooking.booking.service.IInvoiceService;
import com.cinema.ticketbooking.dto.requestDto.InvoiceRequestDto;
import com.cinema.ticketbooking.dto.responseDto.InvoiceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final IInvoiceService invoiceService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<InvoiceResponseDto>> getInvoices(){
        List<InvoiceResponseDto> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDto> getInvoiceById(@PathVariable Integer id){
        InvoiceResponseDto invoice = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/users/{id}")
    public ResponseEntity<List<InvoiceResponseDto>> getInvoicesByUserId(@PathVariable Integer id){
        List<InvoiceResponseDto> invoices = invoiceService.getInvoicesByUserId(id);
        return ResponseEntity.ok(invoices);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/checkout")
    public ResponseEntity<InvoiceResponseDto> createInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto){
        InvoiceResponseDto invoice = invoiceService.createInvoice(invoiceRequestDto);
        return ResponseEntity.ok(invoice);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/change-status/{id}")
    public ResponseEntity<String> updateInvoiceStatus(@PathVariable Integer id, @RequestParam String status){
        try {
            if ("PAID".equalsIgnoreCase(status)) {
                invoiceService.markInvoicePaid(id);
            } else if ("CANCELLED".equalsIgnoreCase(status)) {
                invoiceService.markInvoiceCancelled(id);
            } else {
                return ResponseEntity.badRequest().body("Invalid status: " + status);
            }
            return ResponseEntity.ok("Invoice status updated to " + status + " successfully");
        } catch (IllegalArgumentException e) {
            // Already PAID or invoice not found
            return ResponseEntity.status(409).body("Conflict: " + e.getMessage());
        } catch (IllegalStateException e) {
            // Invoice not in PENDING state
            return ResponseEntity.status(409).body("Conflict: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}
