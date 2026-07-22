package com.cinema.ticketbooking.booking.controller;

import com.cinema.ticketbooking.booking.service.IInvoiceService;
import com.cinema.ticketbooking.booking.service.IVnPayService;
import com.cinema.ticketbooking.dto.requestDto.VnPayCreateUrlRequestDto;
import com.cinema.ticketbooking.dto.responseDto.VnPayCreateUrlResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * ─────────────────────────────────────────────────────────────────────────────
 *  VNPay Payment Controller  –  FILE THỬ NGHIỆM (TEST-ONLY)
 * ─────────────────────────────────────────────────────────────────────────────
 *
 *  Mục đích: Demo luồng thanh toán VNPay Sandbox đúng chuẩn.
 *  File này tạo ra 2 endpoint cần thiết để FE có thể tích hợp VNPay an toàn:
 *
 *    POST /api/vnpay/create-payment-url
 *        FE gọi để lấy URL chuyển hướng đến trang thanh toán VNPay.
 *        BE tạo chữ ký HMAC-SHA512 bằng hashSecret (không lộ ra FE).
 *        BE dùng đồng hồ server → không bị lỗi "quá thời gian giao dịch".
 *
 *    GET  /api/vnpay/return
 *        VNPay redirect user về đây sau khi thanh toán xong (thành công hoặc thất bại).
 *        BE xác thực chữ ký, cập nhật trạng thái invoice, rồi redirect sang FE.
 *
 *  ──────────────────────────────────────────────────────────────────────────
 *  HƯỚNG DẪN CHO DEV BE KHI TÍCH HỢP CHÍNH THỨC:
 *
 *  1. Thêm dependency vào pom.xml (không cần thư viện ngoài, Java tự có HmacSHA512):
 *       Không cần thêm gì – JDK đã có javax.crypto.Mac
 *
 *  2. Thêm config vào application.properties (hoặc application.yml):
 *       vnpay.tmn-code=2QXG2A9Q
 *       vnpay.hash-secret=97BF4G2E0B2D4H1D0B7A6C3B9E1C6D2B
 *       vnpay.url=https://sandbox.vnpayment.vn/paymentv2/vpcpay.html
 *       vnpay.return-url=http://10.111.212.204:8080/api/vnpay/return
 *       vnpay.fe-success-url=http://localhost:5173/booking?vnp_status=success
 *       vnpay.fe-failure-url=http://localhost:5173/booking?vnp_status=failure
 *
 *  3. Mở security cho 2 endpoint này (đã có /api/booking/** trong PathsConfig,
 *     hoặc thêm "/api/vnpay/**" vào publicPaths trong PathsConfig.java).
 *
 *  4. Đảm bảo vnpay.return-url phải là URL mà VNPay Sandbox có thể gọi được.
 *     Trong môi trường dev LAN, dùng IP LAN (192.168.x.x) hoặc ngrok tunnel.
 * ─────────────────────────────────────────────────────────────────────────────
 */
@Slf4j
@RestController
@RequestMapping("/vnpay")
@RequiredArgsConstructor
public class VnPayController {

    private final IVnPayService vnPayService;
    private final IInvoiceService invoiceService;

    /**
     * FE gọi endpoint này để nhận URL thanh toán VNPay.
     *
     * Request body: { invoiceId, amount, clientIp (optional) }
     * Response:     { paymentUrl }
     *
     * FE sau đó làm: window.location.href = response.paymentUrl
     */
    @PostMapping("/create-payment-url")
    public ResponseEntity<VnPayCreateUrlResponseDto> createPaymentUrl(
            @RequestBody VnPayCreateUrlRequestDto request,
            HttpServletRequest httpRequest
    ) {
        // Lấy IP từ request thật nếu FE không gửi
        String clientIp = request.getClientIp();
        if (clientIp == null || clientIp.isBlank()) {
            clientIp = getClientIp(httpRequest);
        }

        String paymentUrl = vnPayService.createPaymentUrl(
                request.getInvoiceId(),
                request.getAmount(),
                clientIp,
                request.getFeOrigin()
        );

        log.info("[VNPay] Created payment URL for invoiceId={}, amount={}", request.getInvoiceId(), request.getAmount());
        return ResponseEntity.ok(new VnPayCreateUrlResponseDto(paymentUrl));
    }

    /**
     * VNPay redirect user về URL này sau khi thanh toán.
     * BE xác thực chữ ký → cập nhật invoice → redirect user sang FE.
     *
     * VNPay gửi các param: vnp_ResponseCode, vnp_TxnRef, vnp_Amount,
     *                      vnp_SecureHash, vnp_TransactionNo, ...
     *
     * vnp_ResponseCode = "00" → thanh toán thành công
     * vnp_ResponseCode != "00" → thất bại hoặc bị huỷ
     */
    @GetMapping("/return")
    public void handleReturn(
            @RequestParam Map<String, String> params,
            jakarta.servlet.http.HttpServletResponse response
    ) throws Exception {
        log.info("[VNPay] Return callback received: {}", params);
        boolean isValid = vnPayService.verifyReturnSignature(params);
        String responseCode = params.getOrDefault("vnp_ResponseCode", "99");
        String txnRef = params.getOrDefault("vnp_TxnRef", "");

        String feOrigin = params.get("fe_origin");

        if (isValid && "00".equals(responseCode)) {
            // Thanh toán thành công → cập nhật invoice PAID
            try {
                int invoiceId = Integer.parseInt(txnRef);
                vnPayService.markInvoicePaid(invoiceId, params);
                log.info("[VNPay] Payment SUCCESS for invoiceId={}", invoiceId);
            } catch (NumberFormatException e) {
                log.error("[VNPay] Invalid txnRef (invoiceId): {}", txnRef);
            }
            response.sendRedirect(vnPayService.getFESuccessUrl(params, feOrigin));
        } else {
            // Chữ ký sai hoặc thanh toán thất bại/huỷ
            log.warn("[VNPay] Payment FAILED or invalid signature. responseCode={}, txnRef={}", responseCode, txnRef);
            try {
                if (!txnRef.isBlank()) {
                    int invoiceId = Integer.parseInt(txnRef);
                    invoiceService.updateInvoiceStatus(invoiceId, "CANCELLED");
                    log.info("[VNPay] Invoice {} marked as CANCELLED due to payment failure.", invoiceId);
                }
            } catch (Exception e) {
                log.error("[VNPay] Failed to mark invoice as CANCELLED after payment failure", e);
            }
            response.sendRedirect(vnPayService.getFEFailureUrl(params, feOrigin));
        }
    }

    // ── Helper: lấy IP thật của client (hỗ trợ cả reverse proxy) ─────────────
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // X-Forwarded-For có thể chứa nhiều IP, lấy IP đầu tiên
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip != null ? ip : "127.0.0.1";
    }
}
