package com.cinema.ticketbooking.booking.service.impl;

import com.cinema.ticketbooking.booking.service.IInvoiceService;
import com.cinema.ticketbooking.dto.requestDto.InvoiceRequestDto;
import com.cinema.ticketbooking.dto.responseDto.InvoiceResponseDto;
import com.cinema.ticketbooking.entity.*;
import com.cinema.ticketbooking.repository.InvoiceDetailRepository;
import com.cinema.ticketbooking.repository.InvoiceRepository;
import com.cinema.ticketbooking.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceServiceImpl implements IInvoiceService {

    @Override
    public InvoiceResponseDto createInvoice(InvoiceRequestDto invoiceRequestDto) {
        return null;
    }

    private final InvoiceRepository invoiceRepository;
    private final TicketRepository ticketRepository;
    private final InvoiceDetailRepository invoiceDetailRepository;


}