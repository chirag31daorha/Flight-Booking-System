package com.jsp.flightbookingsystem.controller;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jsp.flightbookingsystem.dto.ResponseStructure;
import com.jsp.flightbookingsystem.entity.Booking;
import com.jsp.flightbookingsystem.entity.BookingStatus;
import com.jsp.flightbookingsystem.entity.Passenger;
import com.jsp.flightbookingsystem.entity.Payment;
import com.jsp.flightbookingsystem.service.BookingService;
@RestController
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	@PostMapping("/create")
	public ResponseEntity<ResponseStructure<Booking>> createBooking(@RequestBody Booking booking) {
		return bookingService.createBooking(booking);
	}
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBooking(){
		return bookingService.getAllBookings();
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Booking>> getBookingById(@PathVariable Integer id){
		return bookingService.getBookingById(id);
	}
	@GetMapping("/getByFlight/{flightId}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByFlight(@PathVariable Integer flightId){
		return bookingService.getBookingByFlight(flightId);
	}
	@GetMapping("/getByDate/{bookingDate}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByDate(@PathVariable LocalDate bookingDate){
		return bookingService.getBookingByDate(bookingDate);
	}
	@GetMapping("/getByStatus/{status}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(@PathVariable BookingStatus status){
		return bookingService.getBookingByStatus(status);
	}
	@PutMapping("/updateBooking")
	public ResponseEntity<ResponseStructure<Booking>> updateFlight(@RequestBody Booking booking){
		return  bookingService.updateBooking(booking);
	}
	@DeleteMapping("/deleteBooking/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteFlight(@PathVariable Integer id){
		return bookingService.deleteBooking(id);
	}
	@GetMapping("/getAllPassengers/{bookingId}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassengerInABooking(@PathVariable Integer bookingId){
		return bookingService.getAllPassengerInABooking(bookingId);
	}
	@GetMapping("/getPayment/{bookingId}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentDetails(@PathVariable Integer bookingId){
		return bookingService.getPaymentDetails(bookingId);
	}
}
