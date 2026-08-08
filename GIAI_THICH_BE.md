# 📋 BÁO CÁO GIẢI THÍCH LỖI VÀ THAY ĐỔI TRONG BACKEND (SPRING BOOT)

---

## 1. 🔴 VẤN ĐỀ GẶP PHẢI (ISSUE)

Khi Frontend gọi API **`POST /api/halls/{id}/seat-map`** để khởi tạo ma trận danh sách ghế ban đầu cho một phòng chiếu mới, Backend bị văng lỗi **HTTP 500 Internal Server Error**.

### 📄 Log lỗi thực tế từ Backend (`cinema.log`):
```text
org.springframework.dao.InvalidDataAccessApiUsageException: 
org.hibernate.TransientPropertyValueException: 
Persistent instance of 'com.cinema.ticketbooking.entity.Hall' 
references an unsaved transient instance of 'com.cinema.ticketbooking.entity.Seat' 
[com.cinema.ticketbooking.entity.Hall.seats -> com.cinema.ticketbooking.entity.Seat]
```

---

## 2. 🔍 NGUYÊN NHÂN KỸ THUẬT (ROOT CAUSE)

1. Trong entity **`Hall.java`**, thuộc tính `seats` được khai báo như sau:
   ```java
   @OneToMany
   @JoinColumn(name = "hall_id")
   private Set<Seat> seats = new LinkedHashSet<>();
   ```
   👉 Khai báo `@OneToMany` này **không có thuộc tính `cascade = CascadeType.ALL`** (hoặc `PERSIST`).

2. Trong phương thức `generateHallSeatMap` của file **`HallServiceImpl.java`**, đoạn code cũ thực hiện:
   ```java
   hall.getSeats().addAll(newSeats);

   // We only call save once. Hibernate will batch insert the seats.
   hallRepository.save(hall);
   ```
   👉 Do `Hall.java` thiếu `Cascade`, khi gọi `hallRepository.save(hall)`, Hibernate không thể tự động lưu (persist) các đối tượng `Seat` mới trong `newSeats` vào bảng `seat` ➔ Kích hoạt lỗi **`TransientPropertyValueException`**.

---

## 3. ✅ GIẢI PHÁP ĐÃ ĐƯỢC CẬP NHẬT TRONG BACKEND

* **File sửa**: `src/main/java/com/cinema/ticketbooking/hall/service/impl/HallServiceImpl.java`
* **Phương thức**: `generateHallSeatMap(int id, List<SeatDto> seatDtos)`
* **Dòng**: ~163 - 165

### 📝 Đoạn mã thay đổi (Diff):

```diff
  for (SeatDto dto : seatDtos) {
      Seat seat = new Seat();
      seat.setHall(hall);
      seat.setSeatType(type);
      seat.setRowLabel(dto.rowLabel());
      seat.setColNumber(dto.colNumber());
      seat.setStatus("ON");

      newSeats.add(seat);
  }

- hall.getSeats().addAll(newSeats);
- // We only call save once. Hibernate will batch insert the seats.
- hallRepository.save(hall);

+ // Save all seats directly via seatRepository to avoid Cascade issues
+ seatRepository.saveAll(newSeats);

  return newSeats.stream().map(this::transformToDto).toList();
```

---

## 4. 💡 TẠI SAO CÁCH NÀY TỐI ƯU VÀ AN TOÀN?

1. **Bỏ qua rào cản Cascade**: Dùng trực tiếp `seatRepository.saveAll(newSeats)` để chèn các đối tượng `Seat` vào bảng `seat` trong CSDL MySQL mà không phụ thuộc vào cấu hình Cascade của `Hall`.
2. **Hiệu năng & Transaction**: Hàm vẫn chạy trong annotation `@Transactional`, Spring Data JPA sẽ tự động batch insert toàn bộ danh sách ghế trong 1 Transaction duy nhất ➔ Đảm bảo tính toàn vẹn dữ liệu (ACID) 100%.
