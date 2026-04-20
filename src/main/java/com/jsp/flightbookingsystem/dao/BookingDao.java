package com.jsp.flightbookingsystem.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.flightbookingsystem.entity.Booking;
import com.jsp.flightbookingsystem.entity.BookingStatus;
import com.jsp.flightbookingsystem.entity.Passenger;
import com.jsp.flightbookingsystem.entity.Payment;
import com.jsp.flightbookingsystem.exception.IdNotFoundException;
import com.jsp.flightbookingsystem.repository.BookingRepository;

@Repository
public class BookingDao {
	@Autowired
	private BookingRepository bookingRepository;
	
	public Booking createBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}
	public Optional<Booking> getBookingById(Integer id) {
		return bookingRepository.findById(id);
	}
	public List<Booking> getBookingByFlight(Integer flightId) {
		return bookingRepository.findAllByFlightId(flightId);
	}
	public List<Booking> getBookingByDate(LocalDate bookingDate){
		return bookingRepository.getBookingByBookingDate(bookingDate);
	}
	public List<Booking> getBookingByStatus(BookingStatus status){
		return bookingRepository.getBookingByStatus(status);
	}
	public Booking updateBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
	public void deleteBooking(Integer id) {
			bookingRepository.deleteById(id);;
	}
	public Optional<Booking> getAllPassengersInABooking(Integer bookingId){
	    return bookingRepository.findById(bookingId);
	}
	public Optional<Booking> getPaymentDetails(Integer bookingId) {
		return bookingRepository.findById(bookingId);
	}
}

