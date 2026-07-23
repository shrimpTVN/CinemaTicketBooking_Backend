package com.cinema.ticketbooking.dto.requestDto;

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
        int invoiceId,
        long amount,
        String clientIp,
        String feOrigin
) { }

