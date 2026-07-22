package com.cinema.ticketbooking.booking.service;

import com.cinema.ticketbooking.dto.PaymentMethodDto;

import java.util.List;

public interface IPaymentMethodService {
    List<PaymentMethodDto> getAllPaymentMethods();

    PaymentMethodDto getPaymentMethodById(Integer id);

    PaymentMethodDto createPaymentMethod(PaymentMethodDto paymentMethodDto);

    PaymentMethodDto updatePaymentMethod(Integer id, PaymentMethodDto paymentMethodDto);


}
