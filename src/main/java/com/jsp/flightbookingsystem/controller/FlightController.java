package com.jsp.flightbookingsystem.controller;

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
import com.jsp.flightbookingsystem.entity.Flight;
import com.jsp.flightbookingsystem.service.FlightService;
@RestController
@RequestMapping("/flight")
public class FlightController {
	@Autowired
	private FlightService flightService;
	@PostMapping("/addFlight")
	public ResponseEntity<ResponseStructure<Flight>> addFlight(@RequestBody Flight flight){
		return flightService.addFlight(flight);
	}
	@GetMapping("/getAllFlight")
	public ResponseEntity<ResponseStructure<List<Flight>>> getAllFlight(){
		return flightService.getAllFlights();
	}
	@GetMapping("/getById/{id}")
	public ResponseEntity<ResponseStructure<Flight>> getFlightById(@PathVariable Integer id){
		return flightService.getFlightById(id);
	}
	@GetMapping("/{source}/{destination}")
	public ResponseEntity<ResponseStructure<Flight>> getFlightBySourceAndDestination(@PathVariable String source, @PathVariable String destination){
		return flightService.getFlightBySourceAndDestination(source, destination);
	}
	@GetMapping("/{airline}")
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightByAirline(@PathVariable String airline){
		return flightService.getFlightByAirline(airline);
	}
	@PutMapping("/updateFlight")
	public ResponseEntity<ResponseStructure<Flight>> updateFlight(@RequestBody Flight flight){
		return  flightService.updateFlight(flight);
	}
	@DeleteMapping("/deleteFlight/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteFlight(@PathVariable Integer id){
		return flightService.deleteFlight(id);
	}
}