package com.cinema.ticketbooking.dto.requestDto;

import lombok.Data;

/**
 * DTO FE gửi lên để tạo URL thanh toán VNPay.
 *
 * Ví dụ request body:
 * {
 *   "invoiceId": 42,
 *   "amount": 190000,
 *   "clientIp": "192.168.0.100"   // optional, BE tự lấy nếu không có
 * }
 */
@Data
public class VnPayCreateUrlRequestDto {

    /** ID hoá đơn – dùng làm vnp_TxnRef */
    private int invoiceId;

    /** Số tiền VND (chưa nhân 100, BE sẽ nhân khi gửi sang VNPay) */
    private long amount;

    /** IP client – nếu null/blank thì BE tự lấy từ HttpServletRequest */
    private String clientIp;
}
