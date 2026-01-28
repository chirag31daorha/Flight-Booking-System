package com.jsp.flightbookingsystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.flightbookingsystem.entity.Booking;
import com.jsp.flightbookingsystem.entity.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	List<Booking> findAllByFlightId(Integer flightId);
	List<Booking> getBookingByBookingDate(LocalDate bookingDate);
	List<Booking> getBookingByStatus(BookingStatus status);
}
