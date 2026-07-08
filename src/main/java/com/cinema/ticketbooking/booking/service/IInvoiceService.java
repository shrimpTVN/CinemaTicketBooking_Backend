package com.cinema.ticketbooking.booking.service;

import com.cinema.ticketbooking.dto.requestDto.InvoiceRequestDto;
import com.cinema.ticketbooking.dto.responseDto.InvoiceResponseDto;

import java.util.List;

public interface IInvoiceService {
    public List<InvoiceResponseDto> getAllInvoices();
    public InvoiceResponseDto getInvoiceById(Integer id);
    public List<InvoiceResponseDto> getInvoicesByUserId(Integer userId);

    public InvoiceResponseDto createInvoice(InvoiceRequestDto invoiceRequestDto);
//    public InvoiceResponseDto updateInvoice(InvoiceRequestDto invoiceRequestDto);
    public void updateInvoiceStatus(Integer id, String status);
}
