package com.jsp.flightbookingsystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.flightbookingsystem.dao.BookingDao;
import com.jsp.flightbookingsystem.dto.ResponseStructure;
import com.jsp.flightbookingsystem.entity.Booking;
import com.jsp.flightbookingsystem.entity.BookingStatus;
import com.jsp.flightbookingsystem.entity.Flight;
import com.jsp.flightbookingsystem.entity.Passenger;
import com.jsp.flightbookingsystem.entity.Payment;
import com.jsp.flightbookingsystem.exception.IdNotFoundException;
import com.jsp.flightbookingsystem.repository.BookingRepository;
import com.jsp.flightbookingsystem.repository.FlightReopsitory;

import jakarta.transaction.Transactional;

@Service
public class BookingService {
	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private FlightReopsitory flightReopsitory;
	@Transactional
	public ResponseEntity<ResponseStructure<Booking>> createBooking(Booking booking){
		if(booking.getPassengers()!=null) {
			for(Passenger p : booking.getPassengers()) {
				p.setBooking(booking);
			}
		}
		Payment payment =booking.getPayment();
		if(payment!=null) {
			payment.setBooking(booking);
			booking.setPayment(payment);
		}
		Integer flightId= booking.getFlight().getId();
		if(flightId==null) {
			throw new RuntimeException("Flight ID cannot be null");
		}
		Flight flight=flightReopsitory.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found with ID :"+flightId));
		booking.setFlight(flight);
		ResponseStructure<Booking> response=new ResponseStructure<Booking>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Booking is created");
		response.setData(bookingDao.createBooking(booking));
		return new ResponseEntity<ResponseStructure<Booking>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings(){
		ResponseStructure<List<Booking>> response=new ResponseStructure<List<Booking>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("A Bookings List : ");
		response.setData(bookingDao.getAllBookings());
		return new ResponseEntity<ResponseStructure<List<Booking>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Booking>> getBookingById(Integer id){
		Optional<Booking> opt=bookingDao.getBookingById(id);
		if(opt.isEmpty()) {
			throw new IdNotFoundException("Booking with id : "+id+" is not found");
		}
		else {
			Booking booking =opt.get();
			ResponseStructure<Booking> response=new ResponseStructure<Booking>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Booking details for the given booking_id");
			response.setData(booking);
			return new ResponseEntity<ResponseStructure<Booking>>(response, HttpStatus.OK);
		}
	}
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByFlight(Integer flightId){
		ResponseStructure<List<Booking>> response=new ResponseStructure<List<Booking>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("list of bookings for the given flight are available");
		response.setData(bookingDao.getBookingByFlight(flightId));
		return new ResponseEntity<ResponseStructure<List<Booking>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByDate(LocalDate bookingDate){
		ResponseStructure<List<Booking>> response=new ResponseStructure<List<Booking>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("list of bookings for the given date are available");
		response.setData(bookingDao.getBookingByDate(bookingDate));
		return new ResponseEntity<ResponseStructure<List<Booking>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(BookingStatus status){
		ResponseStructure<List<Booking>> response=new ResponseStructure<List<Booking>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("list of booking according to status");
		response.setData(bookingDao.getBookingByStatus(status));
		return new ResponseEntity<ResponseStructure<List<Booking>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Booking>> updateBooking(Booking booking){
		if(booking.getId()==null) {
			throw new IdNotFoundException("ID cannot be null");
		}
		Optional<Booking> existingBooking=bookingDao.getBookingById(booking.getId());
		if(existingBooking.isEmpty()) {
			throw new IdNotFoundException("Booking with id mentioned not found");
		}
		else {
			ResponseStructure<Booking> response=new ResponseStructure<Booking>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Booking record updated");
			response.setData(bookingDao.updateBooking(booking));
			return new ResponseEntity<ResponseStructure<Booking>>(response, HttpStatus.OK);
		}
		
	}
	public ResponseEntity<ResponseStructure<String>> deleteBooking(Integer id){
		Optional<Booking> opt=bookingDao.getBookingById(id);
		if(opt.isEmpty()) {
			throw new IdNotFoundException("Booking with id mentioned not found");
		}
		else {
			ResponseStructure<String> response=new ResponseStructure<String>();
			bookingDao.deleteBooking(id);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Booking Deleted successfully");
			response.setData("Booking deleted with id : "+id);
			return new ResponseEntity<ResponseStructure<String>>(HttpStatus.OK);
		}
		
		
	}
	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassengerInABooking(Integer bookingId){
		Optional<Booking> opt=bookingDao.getBookingById(bookingId);
		if(opt.isEmpty()) {
			throw new IdNotFoundException("Booking not found");
		}	
		else {
			Booking booking=opt.get();
		    List<Passenger> passengers=booking.getPassengers();
		    ResponseStructure<List<Passenger>> response=new ResponseStructure<List<Passenger>>();
		    response.setStatusCode(HttpStatus.OK.value());
		    response.setMessage("Passenegrs in booking ID : "+bookingId);
		    response.setData(passengers);
		    return new ResponseEntity<ResponseStructure<List<Passenger>>>(response, HttpStatus.OK);
		}
	}
	public ResponseEntity<ResponseStructure<Payment>> getPaymentDetails(Integer bookingId){
		Optional<Booking> opt = bookingDao.getBookingById(bookingId);
		if(opt.isEmpty()) {
			throw new IdNotFoundException("Payment not found");
		}
		else {
			Booking booking=opt.get();
			Payment payment=booking.getPayment();
			ResponseStructure<Payment> response=new ResponseStructure<Payment>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Payment Details for bookingId : "+bookingId);
			response.setData(payment);
			return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.OK);
		}
	}
}
