package com.jsp.flightbookingsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.flightbookingsystem.dao.BookingDao;
import com.jsp.flightbookingsystem.dao.FlightDao;
import com.jsp.flightbookingsystem.dao.PassengerDao;
import com.jsp.flightbookingsystem.dto.ResponseStructure;
import com.jsp.flightbookingsystem.entity.Booking;
import com.jsp.flightbookingsystem.entity.Flight;
import com.jsp.flightbookingsystem.entity.Passenger;
import com.jsp.flightbookingsystem.exception.IdNotFoundException;

@Service
public class PassengerService {
	@Autowired
	private PassengerDao passengerDao;
	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private FlightDao flightDao;
	public ResponseEntity<ResponseStructure<Passenger>> addPassenger(Passenger passenger, Integer bookingId){
          Optional<Booking> opt = bookingDao.getBookingById(bookingId);
          if (opt.isEmpty()) {
            throw new IdNotFoundException("Booking not found with id: " + bookingId);
          }
          else {
        	  Booking booking=opt.get();
        	  passenger.setBooking(booking);
              booking.getPassengers().add(passenger);
              Passenger savedPassenger=passengerDao.addPassenger(passenger);
              ResponseStructure<Passenger> response = new ResponseStructure<>();
              response.setStatusCode(HttpStatus.CREATED.value());
              response.setMessage("Passenger saved");
              response.setData(savedPassenger);
    		return new ResponseEntity<ResponseStructure<Passenger>>(response, HttpStatus.CREATED);
          }
	}
	public ResponseEntity<ResponseStructure<List<Passenger>>> getAll(){
		ResponseStructure<List<Passenger>> response=new ResponseStructure<List<Passenger>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("List of All Passengers");
		response.setData(passengerDao.getAll());
		return new ResponseEntity<ResponseStructure<List<Passenger>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(Integer id){
		ResponseStructure<Passenger> response=new ResponseStructure<Passenger>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Passenger details of passengerId : "+id);
		response.setData(passengerDao.getPassengerById(id));
		return new ResponseEntity<ResponseStructure<Passenger>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerByContactNumber(String contactNumber){
		Passenger passenger=passengerDao.getPassengerByContactNumber(contactNumber);
		if(passenger==null) {
			throw new IdNotFoundException("Passenger with this contact number does not exist");
		}
		ResponseStructure<Passenger> response=new ResponseStructure<Passenger>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Paasenger found by contact number : "+contactNumber);
		response.setData(passenger);
		return new ResponseEntity<ResponseStructure<Passenger>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Passenger>> updatPassenger(Passenger passenger){
		Passenger updated=passengerDao.updatePassenger(passenger);
		if(updated==null) {
			throw new IdNotFoundException("Passenger id not found");
		}
		ResponseStructure<Passenger> response= new ResponseStructure<Passenger>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Passenger details updated successfully");
		response.setData(updated);
		return new ResponseEntity<ResponseStructure<Passenger>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersByFlight(Integer flightId){
	    Flight flight = flightDao.getFlightById(flightId);
	    if(flight == null){
	        throw new IdNotFoundException("Flight not found with id: " + flightId);
	    }
	    List<Passenger> passengers = new ArrayList<>();
	    List<Booking> bookings = flight.getBookings();   // ensure you have this mapping
	    for(Booking b : bookings){
	        passengers.addAll(b.getPassengers());
	    }
	    if(passengers.isEmpty()) {
	    	throw new IdNotFoundException("passengers are not available for the flightId : "+flightId);
	    }
	    ResponseStructure<List<Passenger>> response = new ResponseStructure<>();
	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Passengers fetched successfully");
	    response.setData(passengers);
	    return new ResponseEntity<ResponseStructure<List<Passenger>>>(response, HttpStatus.OK);
	}
}
