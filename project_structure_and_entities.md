# Backend Project Structure and Entity Model

This document describes a clean-architecture backend structure for a high-concurrency cinema ticket booking API built with Spring Boot, MySQL, and ReactJS.

## 1. Project Structure

Recommended package layout:

```text
com.cinema.ticketbooking
├── TicketbookingApplication.java
├── core
│   ├── config
│   ├── exception
│   ├── audit
│   └── security
├── domain
│   ├── entity
│   ├── repository
│   └── enum
├── application
│   ├── dto
│   │   ├── request
│   │   └── response
│   ├── service
│   ├── usecase
│   └── mapper
├── infrastructure
│   ├── persistence
│   │   ├── jpa
│   │   └── repository
│   ├── payment
│   └── messaging
└── presentation
    └── controller
```

### Layer responsibilities

| Layer | Responsibility |
|---|---|
| `presentation` | REST controllers, request validation, response mapping |
| `application` | Business use cases such as booking, payment, seat holding |
| `domain` | Core entities, repository contracts, and domain rules |
| `infrastructure` | JPA implementations, external payment adapters, messaging, caching |
| `core` | Cross-cutting concerns such as exceptions, auditing, security, configuration |

## 2. Core Entities

### 2.1 User

Represents a customer, staff member, or admin.

Key fields:
- `id`
- `name`
- `dob`
- `gender`
- `phoneNumber`
- `email`
- `password`
- `role`
- `status`
- `point`

### 2.2 Cinema

Represents a physical cinema branch. This is required for multi-cinema support.

Key fields:
- `id`
- `name`
- `address`
- `phoneNumber`
- `status`

### 2.3 Hall

Represents a screening room inside a cinema.

Key fields:
- `id`
- `cinemaId`
- `name`
- `width`
- `height`
- `images`
- `description`
- `convenience`
- `status`

### 2.4 Seat

Represents a fixed seat inside a hall.

Key fields:
- `id`
- `hallId`
- `rowLabel`
- `colNumber`
- `seatTypeId`
- `status`

### 2.5 SeatType

Represents a seat category such as Normal, VIP, or Couple.

Key fields:
- `id`
- `name`
- `priceSurcharge`
- `description`
- `image`
- `status`

### 2.6 Movie

Represents a movie that can be shown in one or more showtimes.

Key fields:
- `id`
- `title`
- `duration`
- `avatar`
- `trailer`
- `description`
- `country`
- `ageLimit`
- `premiereDate`
- `rating`
- `actors`
- `director`
- `status`

### 2.7 Genre

Represents movie genre classification.

Key fields:
- `id`
- `name`
- `description`

### 2.8 Showtime

Represents a scheduled movie screening in a hall.

Key fields:
- `id`
- `movieId`
- `hallId`
- `date`
- `startTime`
- `type`
- `status`

### 2.9 ShowtimeSeat

Represents the seat availability state for a specific showtime.

Key fields:
- `showtimeId`
- `seatId`
- `status`
- `holdExpiresAt`
- `version`

### 2.10 Booking

Represents a seat reservation attempt and the lifecycle of a hold, confirmation, or cancellation.

Key fields:
- `id`
- `userId`
- `showtimeId`
- `bookingCode`
- `status` (`HELD`, `CONFIRMED`, `CANCELLED`, `EXPIRED`)
- `totalAmount`
- `holdExpiresAt`
- `createdAt`

### 2.11 Ticket

Represents the issued admission ticket after successful booking/payment.

Key fields:
- `id`
- `bookingId`
- `showtimeId`
- `seatId`
- `qrCode`
- `price`
- `status`

### 2.12 Payment

Represents payment transaction data for a booking.

Key fields:
- `id`
- `bookingId`
- `paymentMethod`
- `amount`
- `vat`
- `status`
- `transactionRef`

### 2.13 Product / Invoice / InvoiceDetail

These support concession sales and unified billing.

- `Product`: snack/drink items
- `Invoice`: parent billing record
- `InvoiceDetail`: line items for products

## 3. Relationships (ERD)

### Core relationships

- A **Cinema** has many **Halls**.
- A **Hall** belongs to one **Cinema**.
- A **Hall** has many **Seats**.
- A **Seat** belongs to one **Hall**.
- A **Seat** belongs to one **SeatType**.
- A **Movie** has many **Genres** and a **Genre** has many **Movies**.
- A **Showtime** belongs to one **Hall** and one **Movie**.
- A **Showtime** has many **ShowtimeSeats**.
- A **ShowtimeSeat** belongs to one **Showtime** and one **Seat**.
- A **User** has many **Bookings**.
- A **Booking** belongs to one **User** and one **Showtime**.
- A **Booking** has many **Tickets**.
- A **Booking** has one **Payment**.
- A **Payment** belongs to one **Booking**.
- A **Ticket** belongs to one **Booking**, one **Showtime**, and one **Seat**.

### Cardinality summary

| Relationship | Type |
|---|---|
| Cinema → Hall | One-to-Many |
| Hall → Seat | One-to-Many |
| Hall → Showtime | One-to-Many |
| Movie → Showtime | One-to-Many |
| Movie ↔ Genre | Many-to-Many |
| Showtime → ShowtimeSeat | One-to-Many |
| Seat → ShowtimeSeat | One-to-Many |
| User → Booking | One-to-Many |
| Booking → Ticket | One-to-Many |
| Booking → Payment | One-to-One |
| Booking → Seat (through ShowtimeSeat/Ticket) | Many-to-Many over time, resolved by join entities |

### Current codebase mapping

The current repository already contains these concrete entities:
- `User`
- `Hall`
- `Seat`
- `Showtime`
- `ShowtimeSeat`
- `Movie`
- `Genre`
- `Ticket`
- `Invoice`
- `InvoiceDetail`
- `Product`

For full multi-cinema support, add a `Cinema` entity and make `Hall` reference it with `cinema_id`.

## 4. Database Locking Strategy

To prevent double-booking under high traffic:

### Use optimistic locking

Recommended on:
- `Booking`
- `Payment`
- `Showtime`

Why:
- These records are updated by business workflows and can safely retry on concurrent modification.
- Optimistic locking with a `@Version` field keeps throughput high.

### Use pessimistic locking

Recommended on:
- `ShowtimeSeat`
- Seat-hold operations in the booking flow

Why:
- The same seat for the same showtime must not be held by two users at the same time.
- Use `SELECT ... FOR UPDATE` or `LockModeType.PESSIMISTIC_WRITE` when marking a seat as `HELD` or `BOOKED`.

### Practical booking rule

1. Lock the target `ShowtimeSeat` rows pessimistically.
2. Verify all requested seats are still `AVAILABLE`.
3. Create `Booking` with status `HELD`.
4. Create `Payment` only after hold confirmation or at checkout.
5. Release the hold automatically when `holdExpiresAt` is reached.

## 5. Sample Booking Payloads

### 5.1 Create booking request

```json
{
  "userId": 12,
  "showtimeId": 301,
  "seatIds": [1001, 1002],
  "holdMinutes": 5,
  "paymentMethod": "VNPAY"
}
```

### 5.2 Create booking response

```json
{
  "bookingId": 90001,
  "bookingCode": "BK-20260623-9F2A1C",
  "status": "HELD",
  "userId": 12,
  "showtime": {
    "showtimeId": 301,
    "movieTitle": "Inception",
    "hallName": "Hall A",
    "startTime": "19:30:00",
    "showDate": "2026-06-24"
  },
  "seats": [
    {
      "seatId": 1001,
      "rowLabel": "E",
      "colNumber": 7,
      "seatType": "VIP",
      "status": "HELD"
    },
    {
      "seatId": 1002,
      "rowLabel": "E",
      "colNumber": 8,
      "seatType": "VIP",
      "status": "HELD"
    }
  ],
  "pricing": {
    "subtotal": 180000,
    "vat": 10,
    "totalAmount": 198000
  },
  "holdExpiresAt": "2026-06-23T23:34:59Z",
  "payment": {
    "paymentId": 70001,
    "method": "VNPAY",
    "status": "PENDING"
  }
}
```

### 5.3 Suggested booking response states

- `HELD`: seats are reserved temporarily
- `CONFIRMED`: payment succeeded and tickets may be issued
- `CANCELLED`: user or system cancelled the booking
- `EXPIRED`: hold window elapsed without payment

## 6. Notes for Implementation

- Keep entity classes in `domain.entity` and expose DTOs only through controllers.
- Use repository interfaces in the domain layer and JPA implementations in infrastructure.
- Add a `@Version` column to high-contention aggregates.
- Model seat availability per showtime with a dedicated join entity instead of reusing static `Seat.status`.
- Keep `Invoice` for concession/billing flow and `Booking` for seat reservation flow.
