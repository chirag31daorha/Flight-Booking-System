# ✈️ Flight Booking System

A backend REST API application built with Spring Boot that simulates a real-world flight booking platform. This project covers complete CRUD operations, complex entity relationships, Enum-based status tracking, custom repository queries using Spring Data JPA and Hibernate, and **JWT-based Authentication & Authorization**.

---

## 🛠️ Tech Stack

- **Java** (Spring Boot)
- **Spring Security** (Authentication & Authorization)
- **JWT** (JSON Web Tokens)
- **Hibernate / Spring Data JPA**
- **PostgreSQL**
- **REST APIs**
- **Maven**
- **Postman** (for API testing)

---

## 🔐 Authentication & Authorization

The system uses **JWT (JSON Web Token)** based stateless authentication.

### How it works
1. User registers or logs in via `/user/register` or `/user/login`
2. Server validates credentials and returns a **JWT token**
3. Client sends the token in the `Authorization` header for every protected request
4. `JwtFilter` intercepts each request and validates the token
5. Based on the user's **Role**, access to endpoints is granted or denied

### Security Components

| Component | Description |
|---|---|
| `JwtUtil` | Generates and validates JWT tokens |
| `JwtFilter` | Intercepts requests and checks token validity |
| `SecurityConfig` | Configures Spring Security — public vs protected routes |
| `UserController` | Handles register and login endpoints |
| `UserService` | Business logic for user management |
| `UserDao` | Loads user details for authentication |
| `UserRepository` | Database layer for user data |

### How to use the token

After login, copy the token from the response and add it to every request header:
```
Authorization: Bearer <your-jwt-token>
```

---

## 📦 Entity Design

### User
| Field | Details |
|---|---|
| id | Primary Key |
| username | String |
| password | Encoded (BCrypt) |
| role | Enum (ROLE_USER, ROLE_ADMIN) |

### Passenger
| Field | Details |
|---|---|
| id | Primary Key |
| name | String |
| age | int |
| gender | String |
| contactNo | String |
| seatNumber | String |

### Flight
| Field | Details |
|---|---|
| id | Primary Key |
| airline | String |
| source | String |
| destination | String |
| departureDateTime | Auto-generated |
| arrivalDateTime | Auto-generated |
| totalSeats | int |
| price | double |

### Booking
| Field | Details |
|---|---|
| id | Primary Key |
| bookingDate | Auto-generated |
| status | Enum (CONFIRMED, CANCELLED, etc.) |

### Payment
| Field | Details |
|---|---|
| id | Primary Key |
| paymentDate | Auto-generated |
| amount | double |
| modeOfTransaction | Enum (stored as STRING) |
| status | Enum (stored as STRING) |

---

## 🔗 Entity Relationships

| Relationship | Type |
|---|---|
| Passenger → Booking | One-to-Many (Bidirectional) |
| Booking → Passenger | Many-to-One |
| Booking → Flight | Many-to-One (Bidirectional) |
| Flight → Booking | One-to-Many |
| Booking → Payment | One-to-One (Bidirectional) |

> Bidirectional mappings managed using `@OneToMany`, `@ManyToOne`, `@OneToOne` with `@JsonIgnore` to prevent infinite recursion.

---

## 🔗 API Overview

> All endpoints except `/user/register` and `/user/login` require a valid JWT token in the `Authorization` header.

### 🔐 User APIs (2)
| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | `/user/register` | Register a new user | Public |
| POST | `/user/login` | Login and receive JWT token | Public |

### ✈️ Flight APIs (7)
| Method | Endpoint | Description |
|---|---|---|
| POST | `/flight/addFlight` | Add a new flight |
| GET | `/flight/getAllFlight` | Get all flights |
| GET | `/flight/getById/{id}` | Get flight by ID |
| GET | `/flight/{source}/{destination}` | Get flight by source and destination |
| GET | `/flight/{airline}` | Get flights by airline |
| PUT | `/flight/updateFlight` | Update flight details |
| DELETE | `/flight/deleteFlight/{id}` | Delete a flight |

### 📋 Booking APIs (10)
| Method | Endpoint | Description |
|---|---|---|
| POST | `/booking/create` | Create a new booking |
| GET | `/booking/getAll` | Get all bookings |
| GET | `/booking/getById/{id}` | Get booking by ID |
| GET | `/booking/getByFlight/{flightId}` | Get bookings by flight |
| GET | `/booking/getByDate/{bookingDate}` | Get bookings by date |
| GET | `/booking/getByStatus/{status}` | Get bookings by status |
| PUT | `/booking/updateBooking` | Update booking details |
| DELETE | `/booking/deleteBooking/{id}` | Delete a booking |
| GET | `/booking/getAllPassengers/{bookingId}` | Get all passengers in a booking |
| GET | `/booking/getPayment/{bookingId}` | Get payment details of a booking |

### 👤 Passenger APIs (6)
| Method | Endpoint | Description |
|---|---|---|
| POST | `/passenger/add?bookingId={id}` | Add a passenger to a booking |
| GET | `/passenger/getAll` | Get all passengers |
| GET | `/passenger/getById/{id}` | Get passenger by ID |
| GET | `/passenger/getByNumber/{contactNumber}` | Get passenger by contact number |
| POST | `/passenger/update` | Update passenger info |
| GET | `/passenger/{flightId}` | Get passengers by flight |

### 💳 Payment APIs (8)
| Method | Endpoint | Description |
|---|---|---|
| POST | `/payment/record?bookingId={id}` | Record a payment for a booking |
| GET | `/payment/getAll` | Get all payments |
| GET | `/payment/getById/{paymentId}` | Get payment by ID |
| GET | `/payment/status/{status}` | Get payments by status |
| GET | `/payment/{transaction}` | Get payments by mode of transaction |
| GET | `/payment/getByBooking/{bookingId}` | Get payment by booking |
| PUT | `/payment/{paymentId}/{status}` | Update payment status |
| GET | `/payment/amount/{amount}` | Get payments where amount is greater than given value |

---

## ⚙️ How to Run Locally

### Prerequisites
- Java 17+
- PostgreSQL
- Maven

### Steps

1. Clone the repository
```bash
git clone https://github.com/chirag31daorha/Flight-Booking-System.git
```

2. Create a PostgreSQL database
```sql
CREATE DATABASE flight_booking_db;
```

3. Update `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/flight_booking_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# JWT Config
jwt.secret=your_jwt_secret_key
jwt.expiration=86400000
```

4. Run the application
```bash
mvn spring-boot:run
```

5. Test APIs using Postman on `http://localhost:8080`

---

## 🔑 Testing APIs with Postman

1. Register — `POST /user/register`
2. Login — `POST /user/login` — copy the token from response
3. In Postman go to **Authorization** tab → select **Bearer Token** → paste the token
4. Now call any protected endpoint

---

## 🎯 Key Learnings

- Designing relational databases with proper entity mappings
- Implementing bidirectional relationships using Hibernate
- Using Enums with `@Enumerated(EnumType.STRING)` for status fields
- Building RESTful APIs following layered architecture (Controller → Service → Repository)
- Writing custom JPQL queries in Spring Data JPA repositories
- Handling circular reference issues using `@JsonIgnore`
- Implementing **JWT-based stateless authentication** with Spring Security
- Role-based **authorization** to protect API endpoints
- Password encoding using **BCrypt**
- Securing REST APIs using `JwtFilter` and `SecurityConfig`

---

## 👤 Author

**Chirag Daorha**
[LinkedIn](https://www.linkedin.com/in/chiragdaorha31/) | [GitHub](https://github.com/chirag31daorha)
