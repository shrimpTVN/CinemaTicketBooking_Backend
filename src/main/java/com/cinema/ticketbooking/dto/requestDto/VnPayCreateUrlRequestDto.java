package com.cinema.ticketbooking.dto.requestDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO FE gửi lên để tạo URL thanh toán VNPay.
 * <p>
 * Ví dụ request body:
 * {
 * "invoiceId": 42,
 * "amount": 190000,
 * "clientIp": "192.168.0.100"   // optional, BE tự lấy nếu không có
 * }
 */
public record VnPayCreateUrlRequestDto(
       @NotNull int invoiceId,
        @NotNull @Min(0) long amount,
        String clientIp,
        @NotNull String feOrigin
) { }

