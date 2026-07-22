# Backend Change Documentation (Nhánh: `fix/seat-map-saving`)

Tài liệu này tổng hợp các thay đổi mới nhất trên nhánh **`fix/seat-map-saving`** phục vụ cho Backend Developers nắm bắt thông tin và tiếp tục phát triển.

---

## 📌 Tổng quan công việc
- **Tên nhánh**: `fix/seat-map-saving`
- **Mục tiêu chính**:
  1. Khắc phục và hoàn thiện logic cập nhật/lưu sơ đồ ghế phòng chiếu (Hall Seat Map).
  2. Tích hợp & nâng cấp luồng thanh toán VNPay + Hóa đơn (Invoice).
  3. Bổ sung thông tin người giữ ghế (`holdBy`) và xử lý nhả ghế hết hạn.
  4. Cập nhật cấu hình LAN IP cho Frontend URL.

---

## 🔍 Chi tiết thay đổi kĩ thuật (Technical Details)

### 1. Sơ đồ ghế phòng chiếu (`com.cinema.ticketbooking.hall`)

#### 📄 `HallServiceImpl.java`
- **Tạo mới ghế khi đổi kích thước lưới (`grid resize`)**:
  - Trước đó, `updateHallSeatMap` yêu cầu mọi `SeatDto` phải chứa `id`.
  - Mới: Nếu `dto.id() == null`, hệ thống sẽ tự động khởi tạo đối tượng `Seat` mới, gán `Hall`, và đưa vào danh sách `newSeats` để lưu.
- **Tự động xóa ghế thừa**:
  - Lấy danh sách tất cả ghế hiện có trong DB của phòng chiếu (`findByHallId(id)`).
  - So sánh với danh sách `SeatDto` truyền từ payload Frontend gửi lên.
  - Những ghế có trong DB nhưng không xuất hiện trong payload sẽ được xác định là ghế bị loại bỏ (`seatsToDelete`), hệ thống sẽ thực hiện `seatRepository.deleteAll(seatsToDelete)` và loại khỏi `hall.getSeats()`.
- **Batch Save & Flush**:
  - Gọi `seatRepository.saveAll(newSeats)` và `hallRepository.save(hall)` để đảm bảo dữ liệu được ghi xuống DB chính xác trong `@Transactional`.

---

### 2. Tích hợp thanh toán VNPay & Hóa đơn (`com.cinema.ticketbooking.booking`)

#### 📄 `VnPayCreateUrlRequestDto.java`
- Bổ sung trường `invoiceId` (`Integer`) để liên kết yêu cầu tạo link thanh toán với hóa đơn tương ứng.

#### 📄 `VnPayController.java` & `VnPayServiceImpl.java`
- Cập nhật logic tạo URL thanh toán gửi sang VNPay Sandbox có chứa thông tin `invoiceId`.
- Xử lý các tham số phản hồi (`vnp_ResponseCode`, `vnp_TxnRef`, ...) từ VNPay return URL.

#### 📄 `InvoiceServiceImpl.java` & `InvoiceResponseDto.java`
- **`InvoiceResponseDto`**: Bổ sung thuộc tính `status` (`String`) biểu thị trạng thái hóa đơn (`PENDING`, `PAID`, `CANCELLED`).
- Cập nhật service xử lý hóa đơn tương ứng với trạng thái thanh toán.

#### 📄 `WebConfig.java` & `SecurityConfig.java`
- Cấu hình lại CORS (`allowedOrigins`, `allowedMethods`) để tránh lỗi khi Frontend gọi API thanh toán/callback.
- Bổ sung cấu hình phân quyền cho các endpoint thanh toán VNPay.

---

### 3. Trạng thái & Giữ ghế suất chiếu (`com.cinema.ticketbooking.booking`)

#### 📄 `ShowtimeSeatResponseDto.java`
- Bổ sung thuộc tính `holdBy` (`Integer`): Trả về ID của User đang thực hiện giữ ghế, giúp Frontend hiển thị đúng trạng thái ghế của chính user đó hay người khác.

#### 📄 `ShowtimeSeatRepository.java`
- Bổ sung query method `findExpiredHolds(@Param("now") LocalDateTime now)`: Tìm tất cả bản ghi `ShowtimeSeat` có trạng thái `'HELD'` và `holdUntil <= :now`.

#### 📄 `ShowtimeSeatServiceImpl.java`
- Map thuộc tính `holdBy` từ Entity sang Response DTO.
- Tối ưu hóa xử lý nhả các ghế hết hạn giữ (`releaseExpiredHolds`).

---

### 4. Cấu hình hệ thống

#### 📄 `application.properties`
- Cập nhật `app.frontend-url`:
  ```properties
  app.frontend-url=http://10.13.131.222:5173
  ```
  *(Thay đổi IP LAN để match với địa chỉ máy dev hiện tại)*.

---

## 🛠️ Danh sách file đã chỉnh sửa (Summary of Modified Files)
1. `src/main/java/com/cinema/ticketbooking/hall/service/impl/HallServiceImpl.java`
2. `src/main/java/com/cinema/ticketbooking/booking/controller/VnPayController.java`
3. `src/main/java/com/cinema/ticketbooking/booking/service/IVnPayService.java`
4. `src/main/java/com/cinema/ticketbooking/booking/service/impl/VnPayServiceImpl.java`
5. `src/main/java/com/cinema/ticketbooking/booking/service/impl/InvoiceServiceImpl.java`
6. `src/main/java/com/cinema/ticketbooking/booking/service/impl/ShowtimeSeatServiceImpl.java`
7. `src/main/java/com/cinema/ticketbooking/repository/ShowtimeSeatRepository.java`
8. `src/main/java/com/cinema/ticketbooking/dto/requestDto/VnPayCreateUrlRequestDto.java`
9. `src/main/java/com/cinema/ticketbooking/dto/responseDto/InvoiceResponseDto.java`
10. `src/main/java/com/cinema/ticketbooking/dto/responseDto/ShowtimeSeatResponseDto.java`
11. `src/main/java/com/cinema/ticketbooking/core/config/WebConfig.java`
12. `src/main/java/com/cinema/ticketbooking/core/security/SecurityConfig.java`
13. `src/main/java/com/cinema/ticketbooking/TicketbookingApplication.java`
14. `src/main/resources/application.properties`
