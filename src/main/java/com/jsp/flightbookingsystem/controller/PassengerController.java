package com.jsp.flightbookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.flightbookingsystem.dto.ResponseStructure;
import com.jsp.flightbookingsystem.entity.Passenger;
import com.jsp.flightbookingsystem.service.PassengerService;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
	@Autowired
	private PassengerService passengerService;
	@PostMapping("/add")
	public ResponseEntity<ResponseStructure<Passenger>> addPassenger(@RequestBody Passenger passenger, @RequestParam Integer bookingId ){
		return passengerService.addPassenger(passenger, bookingId);
	}
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getAll(){
		return passengerService.getAll();
	}
	@GetMapping("/getById/{id}")
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(@PathVariable Integer id){
		return passengerService.getPassengerById(id);
	}
	@GetMapping("/getByNumber/{contactNumber}")
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerByContactNumber(@PathVariable String contactNumber){
		return passengerService.getPassengerByContactNumber(contactNumber);
	}
	@PostMapping("/update")
	public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(@RequestBody Passenger passenger){
		return passengerService.updatPassenger(passenger);
	}
	@GetMapping("/{flightId}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getByFlight(@PathVariable Integer flightId){
		return passengerService.getPassengersByFlight(flightId);
	}
}
