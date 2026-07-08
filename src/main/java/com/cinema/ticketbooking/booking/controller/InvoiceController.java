package com.cinema.ticketbooking.booking.controller;

import com.cinema.ticketbooking.booking.service.IInvoiceService;
import com.cinema.ticketbooking.dto.requestDto.InvoiceRequestDto;
import com.cinema.ticketbooking.dto.responseDto.InvoiceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final IInvoiceService invoiceService;

    @GetMapping("")
    public ResponseEntity<List<InvoiceResponseDto>> getInvoices(){
        List<InvoiceResponseDto> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDto> getInvoiceById(@PathVariable Integer id){
        InvoiceResponseDto invoice = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<InvoiceResponseDto>> getInvoicesByUserId(@PathVariable Integer id){
        List<InvoiceResponseDto> invoices = invoiceService.getInvoicesByUserId(id);
        return ResponseEntity.ok(invoices);
    }

    @PostMapping("/checkout")
    public ResponseEntity<InvoiceResponseDto> createInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto){
        InvoiceResponseDto invoice = invoiceService.createInvoice(invoiceRequestDto);
        return ResponseEntity.ok(invoice);
    }

    @PostMapping("/change-status/{id}")
    public ResponseEntity<String> updateInvoiceStatus(@PathVariable Integer id, @RequestParam String status){
        invoiceService.updateInvoiceStatus(id, status);
        return ResponseEntity.ok("Invoice status updated successfully");
    }
}
