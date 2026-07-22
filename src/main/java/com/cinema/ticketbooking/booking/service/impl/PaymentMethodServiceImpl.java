package com.cinema.ticketbooking.booking.service.impl;

import com.cinema.ticketbooking.booking.service.IPaymentMethodService;
import com.cinema.ticketbooking.core.exception.custom.ResourceNotFoundException;
import com.cinema.ticketbooking.dto.PaymentMethodDto;
import com.cinema.ticketbooking.entity.PaymentMethod;
import com.cinema.ticketbooking.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements IPaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethodDto> getAllPaymentMethods() {
        return paymentMethodRepository.findAll()
                .stream()
                .map(this::transformToDto)
                .toList();
    }

    @Override
    public PaymentMethodDto getPaymentMethodById(Integer id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment method not found with id: " + id));
        return transformToDto(paymentMethod);
    }

    @Override
    public PaymentMethodDto createPaymentMethod(PaymentMethodDto paymentMethodDto) {
        requireCreateFields(paymentMethodDto);
        ensureUniqueFields(paymentMethodDto.code(), paymentMethodDto.name(), null);

        PaymentMethod paymentMethod = new PaymentMethod();
        BeanUtils.copyProperties(paymentMethodDto, paymentMethod);

        PaymentMethod savedPaymentMethod = paymentMethodRepository.save(paymentMethod);
        return transformToDto(savedPaymentMethod);
    }

    @Override
    public PaymentMethodDto updatePaymentMethod(Integer id, PaymentMethodDto paymentMethodDto) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment method not found with id: " + id));

        String nextCode = paymentMethodDto.code() != null ? paymentMethodDto.code() : paymentMethod.getCode();
        String nextName = paymentMethodDto.name() != null ? paymentMethodDto.name() : paymentMethod.getName();
        ensureUniqueFields(nextCode, nextName, id);

        if (paymentMethodDto.code() != null) {
            paymentMethod.setCode(paymentMethodDto.code());
        }
        if (paymentMethodDto.name() != null) {
            paymentMethod.setName(paymentMethodDto.name());
        }
        if (paymentMethodDto.description() != null) {
            paymentMethod.setDescription(paymentMethodDto.description());
        }
        if (paymentMethodDto.logo() != null) {
            paymentMethod.setLogo(paymentMethodDto.logo());
        }
        if (paymentMethodDto.surcharge() != null) {
            paymentMethod.setSurcharge(paymentMethodDto.surcharge());
        }
        if (paymentMethodDto.status() != null) {
            paymentMethod.setStatus(paymentMethodDto.status());
        }

        PaymentMethod updatedPaymentMethod = paymentMethodRepository.save(paymentMethod);
        return transformToDto(updatedPaymentMethod);
    }


    private void requireCreateFields(PaymentMethodDto paymentMethodDto) {
        if (paymentMethodDto.code() == null || paymentMethodDto.name() == null
                || paymentMethodDto.description() == null || paymentMethodDto.logo() == null) {
            throw new IllegalArgumentException("code, name, description and logo are required");
        }
    }

    private void ensureUniqueFields(String code, String name, Integer currentId) {
        PaymentMethod existingByCode = paymentMethodRepository.findByCode(code);
        if (existingByCode != null && !existingByCode.getId().equals(currentId)) {
            throw new IllegalArgumentException("Payment method code already exists: " + code);
        }

        PaymentMethod existingByName = paymentMethodRepository.findByName(name);
        if (existingByName != null && !existingByName.getId().equals(currentId)) {
            throw new IllegalArgumentException("Payment method name already exists: " + name);
        }
    }

    private PaymentMethodDto transformToDto(PaymentMethod paymentMethod) {
        return new PaymentMethodDto(
                paymentMethod.getId(),
                paymentMethod.getCode(),
                paymentMethod.getName(),
                paymentMethod.getDescription(),
                paymentMethod.getLogo(),
                paymentMethod.getSurcharge(),
                paymentMethod.getStatus()
        );
    }
}
