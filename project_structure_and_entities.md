# Backend Architecture and REST API Guide

This document explains the backend structure and, more importantly, how to use the REST API from the frontend without reading the source code.

## 1. Tech stack

- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- JWT stored in an HttpOnly cookie

## 2. How authentication works

The API uses a JWT cookie named `cinema_jwt`.

- `POST /auth/login` sets the cookie
- the cookie is `HttpOnly`, so JavaScript cannot read it
- the frontend must send requests with credentials enabled

Use:

- `fetch(..., { credentials: "include" })`
- or Axios with `withCredentials: true`

### Important for FE developers

Do not try to read the JWT from the response body. The token is returned in `Set-Cookie`, and the backend reads it from the cookie on later requests.

## 3. Base API behavior

### Request rules

- JSON request bodies are used for create/update actions
- IDs are usually path parameters
- dates use ISO format:
  - `YYYY-MM-DD`
  - `HH:mm:ss` for time fields

### Response rules

Most endpoints return the saved object or a list of objects directly.

### Route prefix note

The controllers in this codebase are mapped with routes like `/movies`, `/users`, and `/auth`.
If your deployment adds a reverse-proxy prefix such as `/api`, keep the same endpoint path after that prefix.

### Error rules

Validation errors return `400 Bad Request` with a field-to-message map.

General server errors return:

```json
{
  "apiPath": "/some-path",
  "httpStatus": "INTERNAL_SERVER_ERROR",
  "errorMessage": "An unexpected error occurred",
  "errorTime": "2026-07-08T22:18:01"
}
```

Not found errors return `404`, forbidden returns `403`, and seat collision errors return `409`.

## 4. Main API modules

## 4.1 Authentication API

### `POST /auth/login`

Login with email and password.

Request:

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

Response:

```json
{
  "message": "Login Successful",
  "user": {
    "id": 1,
    "name": "User Name",
    "doB": "2000-01-01",
    "point": 0,
    "phoneNumber": "0900000000",
    "email": "user@example.com",
    "role": "USER",
    "status": "ACTIVE"
  }
}
```

Notes:

- sets the `cinema_jwt` cookie
- returns `401` for invalid credentials

### `POST /auth/register`

Create a new user account.

Request:

```json
{
  "name": "User Name",
  "doB": "2000-01-01",
  "point": 0,
  "phoneNumber": "0900000000",
  "email": "user@example.com",
  "password": "password123",
  "roleId": 2,
  "status": "ACTIVE"
}
```

Response:

```json
{
  "id": 1,
  "name": "User Name",
  "doB": "2000-01-01",
  "point": 0,
  "phoneNumber": "0900000000",
  "email": "user@example.com",
  "role": "USER",
  "status": "ACTIVE"
}
```

## 4.2 User API

### `GET /users`

Get all users. Admin only.

### `GET /users/{id}`

Get user by ID. Authenticated user.

### `POST /users`

Create user. Admin only.

### `PATCH /users/{id}`

Update user. Admin only.

### `PATCH /users/{id}/change-password`

Change password for an authenticated user.

Request:

```json
{
  "email": "user@example.com",
  "oldPassword": "old12345",
  "newPassword": "new12345"
}
```

### `PATCH /users/{id}/update-status`

Update user status. Admin only.

Request:

```json
{
  "name": "User Name",
  "doB": "2000-01-01",
  "point": 0,
  "phoneNumber": "0900000000",
  "email": "user@example.com",
  "password": "password123",
  "roleId": 2,
  "status": "INACTIVE"
}
```

## 4.3 Role API

### `GET /roles`

List all roles. Admin only.

### `GET /roles/{id}`

Get role by ID. Admin only.

Response shape:

```json
{
  "id": 1,
  "name": "ADMIN",
  "description": "Administrator",
  "status": "ACTIVE"
}
```

## 4.4 Movie API

### `GET /movies`

List all movies.

### `GET /movies/{id}`

Get movie by ID.

### `GET /movies/special-list?type=SHOWING`

Get movies by code/type.

### `GET /movies/update-special-list`

Refresh the special list and return showing movies.

### `POST /movies`

Create movie. Admin only.

### `PATCH /movies/{id}`

Update movie. Admin only.

Movie DTO uses this shape:

```json
{
  "id": 1,
  "title": "Inception",
  "duration": 148,
  "avatar": "https://...",
  "trailer": "https://...",
  "description": "....",
  "country": "USA",
  "ageLimit": 13,
  "premiereDate": "2026-07-08T00:00:00Z",
  "rating": 8.8,
  "actors": "Leonardo DiCaprio",
  "director": "Christopher Nolan",
  "status": "SHOWING",
  "genres": [
    {
      "id": 1,
      "name": "Sci-Fi",
      "description": "Science fiction"
    }
  ]
}
```

## 4.5 Genre API

### `GET /genres`

List all genres.

### `GET /genres/{id}`

Get genre by ID.

### `POST /genres`

Create genre. Admin only.

### `PATCH /genres/{id}`

Update genre. Admin only.

### `DELETE /genres/{id}`

Delete genre. Admin only.

## 4.6 Hall API

### `GET /halls`

List all halls.

### `GET /halls/{id}`

Get hall by ID.

### `GET /halls/{id}/seat-map`

Get the seat map for a hall.

### `POST /halls/{id}/seat-map`

Generate a seat map for a hall. Admin only.

Request body: array of `SeatDto`

```json
[
  {
    "id": 1,
    "seatTypeId": 2,
    "rowLabel": "A",
    "colNumber": 1,
    "status": "AVAILABLE"
  }
]
```

### `PATCH /halls/{id}/seat-map`

Update a hall seat map. Admin only.

### `POST /halls`

Create hall. Admin only.

### `PATCH /halls/{id}`

Update hall. Admin only.

Hall request:

```json
{
  "name": "Hall A",
  "width": 10,
  "height": 12,
  "hallTypeId": 1
}
```

## 4.7 Hall type API

### `GET /hall-types`

List hall types.

Optional query:

- `?name=IMAX`

### `GET /hall-types/{id}`

Get hall type by ID.

### `POST /hall-types`

Create hall type. Admin only.

### `PATCH /hall-types/{id}`

Update hall type. Admin only.

### `PUT /hall-types/{id}`

Update if exists, otherwise create. Admin only.

Hall type DTO:

```json
{
  "id": 1,
  "name": "Standard",
  "description": "Standard hall",
  "convenience": "Basic",
  "style": "Classic",
  "images": ["https://..."],
  "status": "ACTIVE"
}
```

## 4.8 Seat type API

### `GET /seat-types`

List all seat types.

### `GET /seat-types/{id}`

Get seat type by ID.

### `POST /seat-types`

Create seat type. Admin only.

### `PATCH /seat-types/{id}`

Update seat type. Admin only.

Seat type DTO:

```json
{
  "id": 1,
  "name": "VIP",
  "priceSurcharge": 50000,
  "description": "Premium seat",
  "image": "https://...",
  "status": "ACTIVE"
}
```

## 4.9 Showtime API

### `GET /showtimes`

List all showtimes.

### `GET /showtimes/filter`

Filter showtimes by any combination of:

- `movieId`
- `date`
- `hallId`

Examples:

- `/showtimes/filter?movieId=1`
- `/showtimes/filter?movieId=1&date=2026-07-08`
- `/showtimes/filter?movieId=1&date=2026-07-08&hallId=3`

If no filter is sent, it returns today's showtimes.

### `GET /showtimes/{id}`

Get showtime by ID.

### `POST /showtimes`

Create showtime. Admin only.

### `PATCH /showtimes/{id}`

Update showtime. Admin only.

Showtime request:

```json
{
  "hallId": 1,
  "movieId": 2,
  "date": "2026-07-08",
  "startTime": "19:30:00",
  "type": "2D"
}
```

Showtime response:

```json
{
  "id": 1,
  "hallId": 1,
  "hallName": "Hall A",
  "movieId": 2,
  "movieName": "Inception",
  "date": "2026-07-08",
  "startTime": "19:30:00",
  "type": "2D"
}
```

## 4.10 Showtime seat API

### `GET /showtime-seats/initialize-showtime-seats`

Initialize seat inventory for all showtimes.

### `GET /showtime-seats/showtimes/{id}`

Get all seats for a showtime.

### `GET /showtime-seats/showtimes/{id}/available-count`

Get number of available seats for a showtime.

### `POST /showtime-seats/showtimes/{showtimeId}/hold`

Hold seats for a user.

Request:

```json
{
  "seatIds": [1, 2, 3],
  "userId": 10
}
```

### `POST /showtime-seats/showtimes/{showtimeId}/release`

Release held seats.

Request:

```json
{
  "seatIds": [1, 2, 3],
  "userId": 10
}
```

## 4.11 Price list API

### `GET /price-lists`

List all price lists.

### `GET /price-lists/audience-types/{audienceTypeId}`

Get price lists for an audience type.

### `GET /price-lists/{id}`

Get price list by ID.

### `POST /price-lists/seat-price`

Calculate seat price for a seat, audience type, and date.

Request:

```json
{
  "seatId": 1,
  "audienceTypeId": 2,
  "date": "2026-07-08"
}
```

### `POST /price-lists`

Create price list. Admin only.

### `PATCH /price-lists/{id}`

Update price list. Admin only.

Price list request:

```json
{
  "hallTypeId": 1,
  "seatTypeId": 2,
  "audienceTypeId": 3,
  "name": "Weekend VIP",
  "price": 120000,
  "days": ["SATURDAY", "SUNDAY"]
}
```

## 4.12 Payment method API

### `GET /payment-methods`

List all payment methods.

### `GET /payment-methods/{id}`

Get payment method by ID.

### `POST /payment-methods`

Create payment method. Admin only.

### `PATCH /payment-methods/{id}`

Update payment method. Admin only.

### `DELETE /payment-methods/{id}`

Delete payment method. Admin only.

Payment method DTO:

```json
{
  "id": 1,
  "code": "VNPAY",
  "name": "VNPay",
  "description": "VNPay payment gateway",
  "logo": "https://...",
  "surcharge": 0,
  "status": "ACTIVE"
}
```

## 4.13 Product API

### `GET /products`

List all products.

### `GET /products/{id}`

Get product by ID.

### `POST /products`

Create product. Admin only.

### `PATCH /products/{id}`

Update product. Admin only.

### `POST /products/{id}/update-status`

Update product status. Admin only.

Product DTO:

```json
{
  "id": 1,
  "name": "Popcorn",
  "description": "Large popcorn",
  "price": 50000,
  "image": "https://...",
  "status": "ACTIVE"
}
```

## 4.14 Invoice API

### `GET /invoices`

List all invoices.

### `GET /invoices/{id}`

Get invoice by ID.

### `GET /invoices/users/{id}`

Get invoices by user ID.

### `POST /invoices/checkout`

Create invoice / checkout flow.

Request:

```json
{
  "userId": 1,
  "showtimeId": 2,
  "seatCheckouts": [
    {
      "audienceTypeId": 1,
      "seatIds": [11, 12]
    }
  ],
  "invoiceDetails": [
    {
      "productId": 5,
      "quantity": 2
    }
  ],
  "paymentMethod": "VNPAY"
}
```

### `POST /invoices/change-status/{id}?status=PAID`

Update invoice status.

Invoice response:

```json
{
  "invoiceId": 1,
  "userId": 1,
  "showtime": {
    "id": 2,
    "hallId": 1,
    "hallName": "Hall A",
    "movieId": 3,
    "movieName": "Inception",
    "date": "2026-07-08",
    "startTime": "19:30:00",
    "type": "2D"
  },
  "tickets": [
    {
      "id": 1,
      "showtimeId": 2,
      "audienceType": "Adult",
      "seatRowLabel": "E",
      "seatColNumber": 7,
      "price": 90000
    }
  ],
  "products": [
    {
      "productName": "Popcorn",
      "quantity": 2,
      "price": 100000
    }
  ],
  "paymentMethod": "VNPAY",
  "totalAmount": 190000,
  "vat": 19000
}
```

## 5. Shared DTO quick reference

### User

`UserRequestDto`

```json
{
  "name": "User Name",
  "doB": "2000-01-01",
  "point": 0,
  "phoneNumber": "0900000000",
  "email": "user@example.com",
  "password": "password123",
  "roleId": 2,
  "status": "ACTIVE"
}
```

`UserResponseDto`

```json
{
  "id": 1,
  "name": "User Name",
  "doB": "2000-01-01",
  "point": 0,
  "phoneNumber": "0900000000",
  "email": "user@example.com",
  "role": "USER",
  "status": "ACTIVE"
}
```

### Login

`LoginRequestDto`

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

`LoginResponseDto`

```json
{
  "message": "Login Successful",
  "user": {
    "id": 1,
    "name": "User Name",
    "doB": "2000-01-01",
    "point": 0,
    "phoneNumber": "0900000000",
    "email": "user@example.com",
    "role": "USER",
    "status": "ACTIVE"
  }
}
```

### Seat checkout

`SeatCheckoutRequestDto`

```json
{
  "audienceTypeId": 1,
  "seatIds": [11, 12]
}
```

### Invoice detail

`InvoiceDetailRequestDto`

```json
{
  "productId": 5,
  "quantity": 2
}
```

### Showtime seat

`ShowtimeSeatRequestDto`

```json
{
  "seatIds": [1, 2, 3],
  "userId": 10
}
```

## 6. Frontend integration notes

1. Always send requests with credentials so the JWT cookie is included.
2. Treat `401` as login expired / invalid login.
3. Treat `403` as role denied.
4. Treat `409` as seat already taken or booking conflict.
5. Use `GET /showtimes/filter` and `GET /showtime-seats/...` together to build the booking screen.
6. Use `GET /price-lists/seat-price` before checkout if you need a live price preview.

## 7. Project structure

```text
com.cinema.ticketbooking
|-- auth
|   `-- controller
|-- booking
|   `-- controller
|-- hall
|   `-- controller
|-- movie
|   `-- controller
|-- user
|   `-- controller
|-- core
|   |-- constant
|   |-- exception
|   `-- security
`-- dto
    |-- requestDto
    |-- responseDto
    `-- shared DTOs
```

## 8. Current domain coverage

The current backend already exposes API support for:

- authentication
- users and roles
- movies and genres
- halls, hall types, seat types
- showtimes and showtime seats
- price lists
- payment methods
- products
- invoices and checkout

This document should be enough for the frontend team to understand which endpoints exist, what to send, and what to expect back.
