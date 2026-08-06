# 🎬 Cinema Ticket Booking - Backend API

> Hệ thống Backend RESTful API Quản lý và Đặt vé xem phim trực tuyến được xây dựng trên nền tảng **Spring Boot 4**, **Spring Security**, **MySQL**, tích hợp thanh toán **VNPay**, lưu trữ đa phương tiện **Cloudinary** và giao tiếp thời gian thực qua **WebSocket**.

---

## 📋 Mục lục
1. [Giới thiệu dự án](#-giới-thiệu-dự-án)
2. [Công nghệ sử dụng](#-công-nghệ-sử-dụng-tech-stack)
3. [Tính năng chính](#-tính-năng-chính)
4. [Cấu trúc dự án](#-cấu-trúc-dự-án)
5. [Yêu cầu hệ thống](#-yêu-cầu-hệ-thống)
6. [Cơ chế Xác thực & Bảo mật](#-cơ-chế-xác-thực--bảo-mật)
7. [Tích hợp Thanh toán (VNPay)](#-tích-hợp-thanh-toán-vnpay)
8. [Hướng dẫn dành cho Frontend](#-hướng-dẫn-dành-cho-frontend)
---

## 🚀 Giới thiệu dự án

**Cinema Ticket Booking Backend** là hệ thống cung cấp toàn bộ API dịch vụ cho nền tảng đặt vé xem phim rạp. Hệ thống xử lý các nghiệp vụ phức tạp từ quản lý lịch chiếu, phòng chiếu, loại ghế, tính toán giá vé, xử lý chọn giữ ghế theo thời gian thực (WebSocket), tích hợp cổng thanh toán trực tuyến VNPay, quản lý tích điểm thành viên cho tới phân quyền tài khoản (User & Admin).

---

## 🛠 Công nghệ sử dụng (Tech Stack)

| Công nghệ / Thư viện | Mô tả & Mục đích |
| :--- | :--- |
| **Java 25** | Ngôn ngữ lập trình chính |
| **Spring Boot 4.1.0** | Framework phát triển Backend REST API |
| **Spring Security** | Quản lý xác thực và phân quyền (RBAC) |
| **JWT (HttpOnly Cookie)** | Xác thực người dùng an toàn qua Cookie `cinema_jwt` |
| **Google OAuth 2.0** | Đăng nhập nhanh qua tài khoản Google |
| **Spring Data JPA / Hibernate** | ORM thao tác với Cơ sở dữ liệu |
| **MySQL 8.x** | Hệ quản trị cơ sở dữ liệu quan hệ |
| **Spring WebSocket** | Xử lý cập nhật trạng thái ghế theo thời gian thực |
| **VNPay Sandbox API** | Tích hợp cổng thanh toán trực tuyến |
| **Cloudinary SDK** | Quản lý và lưu trữ hình ảnh (Poster phim, Banner sự kiện) |
| **Springdoc OpenAPI (Swagger)** | Tự động sinh tài liệu API tương tác |
| **Docker / Docker Compose** | Khởi tạo môi trường Database nhanh chóng |
| **Lombok** | Giảm thiểu boilerplate code (Getter, Setter, Builder, v.v.) |

---

## ✨ Tính năng chính

### 🛡️ 1. Xác thực & Tài khoản (Auth & User Management)
- **Đăng ký & Đăng nhập**: Hỗ trợ Đăng ký/Đăng nhập chuẩn Email-Password và Đăng nhập Google OAuth2.
- **Cơ chế JWT Cookie**: Mã JWT được lưu trong Cookie `HttpOnly` giúp chống tấn công XSS.
- **Phân quyền (RBAC)**: Phân chia rõ ràng quyền hạn giữa người dùng (`USER`) và Quản trị viên (`ADMIN`).
- **Quản lý Hồ sơ & Điểm thưởng**: Người dùng xem lịch sử đặt vé, cập nhật thông tin cá nhân.

### 🎥 2. Quản lý Phim & Thể loại (Movies & Genres)
- Quản lý danh sách phim (Đang chiếu, Sắp chiếu, Đánh giá, Thời lượng, Đạo diễn, Diễn viên).
- Upload và quản lý ảnh poster, trailer thông qua dịch vụ Cloudinary.
- Đánh giá & Bình luận (Rating & Review) từ người dùng.

### 🏛️ 3. Quản lý Phòng chiếu & Loại ghế (Halls & Seats)
- Quản lý phòng chiếu (Hall), loại phòng (Standard, IMAX, 4DX,...).
- Sơ đồ ghế ngồi theo từng phòng chiếu, hỗ trợ các loại ghế khác nhau (Ghế Thường, Ghế VIP, Ghế Sweetbox/Đôi).

### ⏰ 4. Lịch chiếu & Bảng giá (Showtimes & Pricing)
- Thiết lập lịch chiếu phim theo phòng chiếu, ngày chiếu và khung giờ.
- Áp dụng chính sách giá linh hoạt dựa trên loại ghế, khung giờ (ngày thường/cuối tuần/lễ) và loại phòng.

### 🍿 5. Đặt vé & Sản phẩm đi kèm (Booking & Concessions)
- Giữ ghế thời gian thực bằng WebSocket giúp tránh tình trạng giữ/đặt trùng ghế (*Seat Collision*).
- Đặt kèm các gói bắp nước, nước giải khát, combo ưu đãi.
- Áp dụng tích điểm thành viên giảm giá trực tiếp vào hóa đơn.

### 💳 6. Thanh toán Trực tuyến (Payment Integration)
- Tích hợp cổng thanh toán **VNPay (Sandbox)**.
- Xử lý kết quả trả về từ cổng thanh toán, tự động cập nhật trạng thái hóa đơn và giải phóng/xác nhận ghế.

---

## 📁 Cấu trúc dự án

```text
ticketbooking/
├── src/
│   ├── main/
│   │   ├── java/com/cinema/ticketbooking/
│   │   │   ├── auth/         # Module Đăng ký, Đăng nhập, JWT, OAuth2
│   │   │   ├── booking/      # Module Đặt vé, Hóa đơn, Lịch chiếu, Thanh toán VNPay
│   │   │   ├── core/         # Exception Handling, Base DTOs, Security Config, Utils
│   │   │   ├── dto/          # Data Transfer Objects
│   │   │   ├── entity/       # Các JPA Entities (User, Movie, Hall, Showtime, Invoice,...)
│   │   │   ├── hall/         # Module Quản lý Phòng chiếu, Sơ đồ ghế, Loại ghế
│   │   │   ├── media/        # Module Upload ảnh Cloudinary, Banner & Sự kiện
│   │   │   ├── movie/        # Module Phim, Thể loại, Đánh giá/Bình luận
│   │   │   ├── repository/   # Spring Data JPA Repositories
│   │   │   └── user/         # Module Quản lý Người dùng & Quyền hạn
│   │   └── resources/
│   │       ├── sql/          # File khởi tạo Schema & Data SQL (cinema_schema.sql, cinema_data.sql)
│   │       ├── application.properties  # Cấu hình ứng dụng chính
│   │       └── application.yml
├── compose.yaml              # Docker Compose khởi chạy MySQL Database
├── pom.xml                   # Cấu hình Maven dependencies
├── HELP.md                   # Tài liệu hỗ trợ mặc định của Spring Boot
└── project_structure_and_entities.md # Tài liệu hướng dẫn tích hợp chi tiết cho Frontend
```

---

## ⚙️ Yêu cầu hệ thống

- **Java Development Kit (JDK)**: Phiên bản 25 (hoặc tương thích 21+)
- **Maven**: 3.8+ (hoặc dùng `mvnw` đi kèm dự án)
- **Docker & Docker Compose**: Để chạy nhanh MySQL Database
- **MySQL Client / DBeaver / Navicat** *(Tùy chọn)*: Quản lý CSDL trực quan

---

## 🔒 Cơ chế Xác thực & Bảo mật

- **JWT trong HttpOnly Cookie**: Khi đăng nhập thành công (`POST /auth/login`), server sẽ tự động ghi cookie có tên `cinema_jwt`. Cookie này mang cờ `HttpOnly`, ngăn chặn hoàn toàn việc đánh cắp token qua JavaScript phía FE.
- **Frontend Request**: Phía Frontend **KHÔNG** cần đọc token từ response body, chỉ cần gọi API kèm tùy chọn gửi credentials:
  - `fetch(url, { credentials: 'include' })`
  - `axios.get(url, { withCredentials: true })`

---

## 💳 Tích hợp Thanh toán VNPay

1. Khi người dùng xác nhận thanh toán hóa đơn đặt vé, FE gọi API tạo URL thanh toán.
2. Server tạo giao dịch VNPay Sandbox và trả về link redirect.
3. Người dùng thực hiện thanh toán trên cổng VNPay.
4. VNPay sẽ redirect người dùng trở lại Frontend dựa theo cấu hình `app.frontend-url` cùng tham số mã kết quả `vnp_ResponseCode`.

---

## 💻 Hướng dẫn dành cho Frontend Developer

- **Cookie Credentials**: Luôn bật `withCredentials: true` trên Axios hoặc `credentials: 'include'` trên Fetch API.
- **Định dạng thời gian**: Tất cả dữ liệu ngày giờ gửi lên server tuân theo chuẩn ISO:
  - Ngày: `YYYY-MM-DD` (Ví dụ: `2026-08-06`)
  - Giờ: `HH:mm:ss` (Ví dụ: `19:30:00`)
- **Xử lý Lỗi chuẩn**: Server trả về thông báo lỗi chuẩn theo định dạng JSON với Http Status Code tương ứng (`400 Bad Request`, `401 Unauthorized`, `403 Forbidden`, `404 Not Found`, `409 Seat Collision`).

---

## 📄 License & Maintainers

- Dự án được phát triển cho hệ thống Đặt vé xem phim **Cinema Ticket Booking**.
- Mọi thắc mắc hoặc đóng góp vui lòng liên hệ đội ngũ phát triển Backend.
