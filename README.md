# ✈️ Flight Booking System

A backend REST API application built with Spring Boot that simulates a 
real-world flight booking platform. This project covers complete CRUD 
operations, complex entity relationships, Enum-based status tracking, 
and custom repository queries using Spring Data JPA and Hibernate.

---

## 🛠️ Tech Stack

- **Java** (Spring Boot)
- **Hibernate / Spring Data JPA**
- **PostgreSQL**
- **REST APIs**
- **Maven**
- **Postman** (for API testing)

---

## 📦 Entity Design

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

> Bidirectional mappings managed using `@OneToMany`, `@ManyToOne`, 
> `@OneToOne` with `@JsonIgnore` to prevent infinite recursion.

---

## 🔗 API Overview (30 REST Endpoints)

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
```

4. Run the application
```bash
   mvn spring-boot:run
```

5. Test APIs using Postman on `http://localhost:8080`

---

## 🎯 Key Learnings

- Designing relational databases with proper entity mappings
- Implementing bidirectional relationships using Hibernate
- Using Enums with `@Enumerated(EnumType.STRING)` for status fields
- Building RESTful APIs following layered architecture
  (Controller → Service → Repository)
- Writing custom JPQL queries in Spring Data JPA repositories
- Handling circular reference issues using `@JsonIgnore`

---

## 👤 Author

**Chirag Daorha**
[LinkedIn](https://www.linkedin.com/in/chiragdaorha31/) |
[GitHub](https://github.com/chirag31daorha)
