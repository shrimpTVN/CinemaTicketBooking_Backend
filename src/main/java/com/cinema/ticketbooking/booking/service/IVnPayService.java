package com.cinema.ticketbooking.booking.service;

import java.util.Map;

/**
 * Interface định nghĩa các nghiệp vụ VNPay cần implement.
 * Dev BE chỉ cần implement interface này vào VnPayServiceImpl.java
 */
public interface IVnPayService {

    /**
     * Tạo URL thanh toán VNPay có chữ ký HMAC-SHA512.
     *
     * @param invoiceId ID của hoá đơn (dùng làm vnp_TxnRef)
     * @param amount    Số tiền thanh toán (VND, chưa nhân 100)
     * @param clientIp  IP của user (VNPay bắt buộc)
     * @return URL đầy đủ để redirect user sang VNPay
     */
    String createPaymentUrl(int invoiceId, long amount, String clientIp);

    /**
     * Xác thực chữ ký HMAC-SHA512 trong callback từ VNPay.
     * Quan trọng: Phải xác thực trước khi cập nhật bất kỳ dữ liệu nào.
     *
     * @param params Toàn bộ query params VNPay trả về
     * @return true nếu chữ ký hợp lệ, false nếu giả mạo
     */
    boolean verifyReturnSignature(Map<String, String> params);

    /**
     * Đánh dấu hoá đơn là đã thanh toán (PAID).
     * Gọi POST /invoices/change-status/{id}?status=PAID của InvoiceService.
     *
     * @param invoiceId ID hoá đơn
     * @param vnpayParams Thông tin giao dịch VNPay (vnp_TransactionNo, ...)
     */
    void markInvoicePaid(int invoiceId, Map<String, String> vnpayParams);

    /**
     * Lấy URL FE để redirect user khi thanh toán thành công.
     * Ví dụ: http://localhost:5173/booking?vnp_status=success&invoiceId=5
     */
    String getFESuccessUrl(Map<String, String> vnpayParams);

    /**
     * Lấy URL FE để redirect user khi thanh toán thất bại/huỷ.
     * Ví dụ: http://localhost:5173/booking?vnp_status=failure&invoiceId=5
     */
    String getFEFailureUrl(Map<String, String> vnpayParams);
}
