package com.cinema.ticketbooking.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response trả về cho FE sau khi tạo URL thanh toán VNPay.
 * <p>
 * Ví dụ response:
 * {
 * "paymentUrl": "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?vnp_Amount=..."
 * }
 * <p>
 * FE dùng: window.location.href = response.paymentUrl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VnPayCreateUrlResponseDto {
    private String paymentUrl;
}
