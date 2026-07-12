package com.cinema.ticketbooking.booking.service;

import com.cinema.ticketbooking.booking.service.IInvoiceService;
import com.cinema.ticketbooking.booking.service.IVnPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * ─────────────────────────────────────────────────────────────────────────────
 *  VnPayServiceImpl  –  FILE THỬ NGHIỆM (TEST-ONLY)
 * ─────────────────────────────────────────────────────────────────────────────
 *
 *  Implement đầy đủ luồng tạo URL và xác thực callback VNPay Sandbox.
 *
 *  Các property cần có trong application.properties:
 *    vnpay.tmn-code=2QXG2A9Q
 *    vnpay.hash-secret=97BF4G2E0B2D4H1D0B7A6C3B9E1C6D2B
 *    vnpay.url=https://sandbox.vnpayment.vn/paymentv2/vpcpay.html
 *    vnpay.return-url=http://192.168.0.241:8080/api/vnpay/return
 *    vnpay.fe-success-url=http://localhost:5173/booking
 *    vnpay.fe-failure-url=http://localhost:5173/booking
 *
 *  Lưu ý quan trọng về thời gian:
 *    - VNPay Sandbox dùng giờ Việt Nam (UTC+7) để kiểm tra vnp_CreateDate.
 *    - Code này lấy giờ server với ZoneId "Asia/Ho_Chi_Minh" → luôn đúng.
 *    - Đây chính là lý do luồng FE bị lỗi "quá thời gian giao dịch":
 *      đồng hồ máy tính dev đang là 2026, FE cố sửa thủ công nhưng bị sai.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VnPayServiceImpl implements IVnPayService {

    // ── Config từ application.properties ─────────────────────────────────────
    @Value("${vnpay.tmn-code}")
    private String tmnCode;

    @Value("${vnpay.hash-secret}")
    private String hashSecret;

    @Value("${vnpay.url}")
    private String vnpUrl;

    @Value("${vnpay.return-url}")
    private String returnUrl;

    @Value("${vnpay.fe-success-url}")
    private String feSuccessUrl;

    @Value("${vnpay.fe-failure-url}")
    private String feFailureUrl;

    // InvoiceService để cập nhật trạng thái sau khi thanh toán
    private final IInvoiceService invoiceService;

    // ── Định dạng thời gian VNPay yêu cầu ────────────────────────────────────
    private static final DateTimeFormatter VNP_DATE_FMT =
            DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    // ── Timezone Việt Nam ─────────────────────────────────────────────────────
    private static final ZoneId VN_ZONE = ZoneId.of("Asia/Ho_Chi_Minh");

    // ─────────────────────────────────────────────────────────────────────────
    @Override
    public String createPaymentUrl(int invoiceId, long amount, String clientIp) {
        // Lấy giờ THẬT của Việt Nam từ đồng hồ server (không bị lỗi timezone)
        String createDate = LocalDateTime.now(VN_ZONE).format(VNP_DATE_FMT);

        // Tập hợp tham số VNPay (đã sort theo alphabet nhờ TreeMap)
        Map<String, String> params = new TreeMap<>();
        params.put("vnp_Version",    "2.1.0");
        params.put("vnp_Command",    "pay");
        params.put("vnp_TmnCode",    tmnCode);
        params.put("vnp_Amount",     String.valueOf(amount * 100L));
        params.put("vnp_CreateDate", createDate);
        params.put("vnp_CurrCode",   "VND");
        params.put("vnp_IpAddr",     clientIp);
        params.put("vnp_Locale",     "vn");
        params.put("vnp_OrderInfo",  "ThanhToanHoaDon_" + invoiceId);
        params.put("vnp_OrderType",  "other");
        params.put("vnp_ReturnUrl",  returnUrl);
        params.put("vnp_TxnRef",     String.valueOf(invoiceId));

        // Bước 1: Chuỗi ký dùng URL-encoded values (chuẩn VNPay official Java SDK)
        // ⚠ QUAN TRỌNG: VNPay server tính hash với giá trị URL-encoded.
        // Dùng raw values sẽ sai chữ ký vì returnUrl chứa :// và / bị encode khác nhau.
        String signData = buildHashData(params);

        // Bước 2: Ký HMAC-SHA512
        String secureHash = hmacSHA512(hashSecret, signData);

        // Bước 3: Build URL cuối (cùng URL-encoded values + chữ ký)
        String queryString = buildHashData(params) + "&vnp_SecureHash=" + secureHash;

        log.info("[VNPay] createDate={}, invoiceId={}, amount={}", createDate, invoiceId, amount);
        log.debug("[VNPay] signData={}", signData);
        return vnpUrl + "?" + queryString;
    }

    // ─────────────────────────────────────────────────────────────────────────
    @Override
    public boolean verifyReturnSignature(Map<String, String> params) {
        String receivedHash = params.get("vnp_SecureHash");
        if (receivedHash == null || receivedHash.isBlank()) return false;

        // Loại bỏ các field chữ ký trước khi tính lại
        Map<String, String> filteredParams = new TreeMap<>(params);
        filteredParams.remove("vnp_SecureHash");
        filteredParams.remove("vnp_SecureHashType");

        String signData = buildHashData(filteredParams);
        String expectedHash = hmacSHA512(hashSecret, signData);

        boolean valid = expectedHash.equalsIgnoreCase(receivedHash);
        if (!valid) {
            log.error("[VNPay] Signature mismatch! expected={}, received={}", expectedHash, receivedHash);
        }
        return valid;
    }

    // ─────────────────────────────────────────────────────────────────────────
    @Override
    public void markInvoicePaid(int invoiceId, Map<String, String> vnpayParams) {
        // Gọi service Invoice có sẵn để cập nhật trạng thái
        invoiceService.updateInvoiceStatus(invoiceId, "PAID");
        log.info("[VNPay] Invoice {} marked as PAID. VNPay TxnNo={}",
                invoiceId, vnpayParams.get("vnp_TransactionNo"));
    }

    // ─────────────────────────────────────────────────────────────────────────
    @Override
    public String getFESuccessUrl(Map<String, String> vnpayParams) {
        String invoiceId = vnpayParams.getOrDefault("vnp_TxnRef", "");
        return feSuccessUrl + "?vnp_status=success&invoiceId=" + invoiceId;
    }

    @Override
    public String getFEFailureUrl(Map<String, String> vnpayParams) {
        String invoiceId = vnpayParams.getOrDefault("vnp_TxnRef", "");
        String code      = vnpayParams.getOrDefault("vnp_ResponseCode", "99");
        return feFailureUrl + "?vnp_status=failure&invoiceId=" + invoiceId + "&code=" + code;
    }

    // ── Utility: build query string (sort alphabet → đúng chuẩn VNPay) ───────
    /**
     * Build chuỗi dữ liệu để ký HMAC theo chuẩn VNPay:
     * - Key sắp xếp alphabet (TreeMap đảm bảo)
     * - Value được URL-encode bằng UTF-8 (bắt buộc để chữ ký khớp với VNPay server)
     *
     * Ví dụ: returnUrl = https://abc.ngrok.io/booking
     *   → được encode thành: https%3A%2F%2Fabc.ngrok.io%2Fbooking
     *   → VNPay server cũng encode giống vậy khi xác thực → khớp chữ ký
     */
    private String buildHashData(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isBlank()) continue;
            if (!sb.isEmpty()) sb.append("&");
            String encodedValue = URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8);
            sb.append(entry.getKey()).append("=").append(encodedValue);
        }
        return sb.toString();
    }

    /** @deprecated Dùng buildHashData() thay thế */
    private String buildQueryString(Map<String, String> params, boolean encode) {
        return buildHashData(params);
    }

    // ── Utility: HMAC-SHA512 (Java tự có, không cần thư viện ngoài) ──────────
    private String hmacSHA512(String key, String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512"));
            byte[] rawHash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : rawHash) hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException("[VNPay] HMAC-SHA512 failed", e);
        }
    }
}
