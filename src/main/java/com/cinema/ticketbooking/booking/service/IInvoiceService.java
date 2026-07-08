package com.cinema.ticketbooking.booking.service;

import com.cinema.ticketbooking.dto.requestDto.InvoiceRequestDto;
import com.cinema.ticketbooking.dto.responseDto.InvoiceResponseDto;

public interface IInvoiceService {
    public InvoiceResponseDto createInvoice(InvoiceRequestDto invoiceRequestDto);
}
