# ✈️ Flight Booking System

A full-stack flight booking platform built with **Spring Boot** (backend) and **React** (frontend). The system covers complete CRUD operations, complex entity relationships, Enum-based status tracking, custom repository queries using Spring Data JPA, and **JWT-based Authentication & Authorization**.

---

## 🛠️ Tech Stack

### Backend
- **Java** (Spring Boot)
- **Spring Security** (Authentication & Authorization)
- **JWT** (JSON Web Tokens)
- **Hibernate / Spring Data JPA**
- **PostgreSQL**
- **REST APIs**
- **Maven**

### Frontend
- **React**
- **Vite**

### Tools
- **Postman** (for API testing)

---

## 🔐 Authentication & Authorization

The system uses **JWT (JSON Web Token)** based stateless authentication.

### How it works
1. User registers or logs in via `/auth/register` or `/auth/login`
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

### Roles

| Role | Access |
|---|---|
| `ROLE_USER` | Can view flights, create bookings, make payments |
| `ROLE_ADMIN` | Full access — manage flights, bookings, passengers, payments |

### Auth Endpoints

| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | `/auth/register` | Register a new user | Public |
| POST | `/auth/login` | Login and get JWT token | Public |

### How to use the token

After login, copy the token from the response and add it to your request headers:
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

## 🔗 API Overview (32 REST Endpoints)

> All endpoints except `/auth/register` and `/auth/login` require a valid JWT token in the `Authorization` header.

### 🔐 Auth APIs (2)
| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | `/auth/register` | Register new user | Public |
| POST | `/auth/login` | Login and receive JWT | Public |

### ✈️ Flight APIs (7)
| Method | Endpoint | Description |
|---|---|---|
| POST | `/flights` | Add a new flight |
| GET | `/flights` | Get all flights |
| GET | `/flights/{id}` | Get flight by ID |
| GET | `/flights/source-destination` | Get flights by source and destination |
| GET | `/flights/airline` | Get flights by airline |
| PUT | `/flights/{id}` | Update flight details |
| DELETE | `/flights/{id}` | Delete a flight |

### 📋 Booking APIs (10)
| Method | Endpoint | Description |
|---|---|---|
| POST | `/bookings` | Create a new booking |
| GET | `/bookings` | Get all bookings |
| GET | `/bookings/{id}` | Get booking by ID |
| GET | `/bookings/flight/{id}` | Get bookings by flight |
| GET | `/bookings/date` | Get bookings by date |
| GET | `/bookings/status/{status}` | Get bookings by status |
| GET | `/bookings/{id}/passengers` | Get all passengers in a booking |
| GET | `/bookings/{id}/payment` | Get payment details of a booking |
| PUT | `/bookings/{id}/status` | Update booking status |
| DELETE | `/bookings/{id}` | Delete a booking |

### 👤 Passenger APIs (6)
| Method | Endpoint | Description |
|---|---|---|
| POST | `/passengers` | Add a passenger |
| GET | `/passengers` | Get all passengers |
| GET | `/passengers/{id}` | Get passenger by ID |
| GET | `/passengers/contact/{no}` | Get passenger by contact number |
| PUT | `/passengers/{id}` | Update passenger info |
| GET | `/passengers/flight/{id}` | Get passengers by flight |

### 💳 Payment APIs (8)
| Method | Endpoint | Description |
|---|---|---|
| POST | `/payments` | Record a payment |
| GET | `/payments` | Get all payments |
| GET | `/payments/{id}` | Get payment by ID |
| GET | `/payments/status/{status}` | Get payments by status |
| GET | `/payments/mode/{mode}` | Get payments by mode of transaction |
| GET | `/payments/booking/{id}` | Get payment by booking |
| PUT | `/payments/{id}/status` | Update payment status |
| GET | `/payments/amount` | Fetch payments where amount is greater than a particular value |

---

## ⚙️ How to Run Locally

### Prerequisites
- Java 17+
- PostgreSQL
- Maven
- Node.js & npm (for frontend)

### Backend Setup

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

4. Run the backend
```bash
mvn spring-boot:run
```

### Frontend Setup

1. Clone the frontend repository
```bash
git clone https://github.com/chirag31daorha/flight-booking-frontend.git
```

2. Install dependencies
```bash
npm install
```

3. Start the development server
```bash
npm run dev
```

4. Open `http://localhost:5173` in your browser

---

## 🔑 Testing APIs with Postman

1. Register a user — `POST /auth/register`
2. Login — `POST /auth/login` — copy the token from response
3. In Postman, go to **Authorization** tab → select **Bearer Token** → paste the token
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
