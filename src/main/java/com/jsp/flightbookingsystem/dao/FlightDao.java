package com.jsp.flightbookingsystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.flightbookingsystem.entity.Flight;
import com.jsp.flightbookingsystem.exception.IdNotFoundException;
import com.jsp.flightbookingsystem.repository.FlightReopsitory;

@Repository
public class FlightDao {
	@Autowired
	private FlightReopsitory flightRepository;
	public Flight addFlight(Flight flight) {
		return flightRepository.save(flight);
	}
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}
	public Flight getFlightById(Integer id) {
		Optional<Flight> opt= flightRepository.findById(id);
		if(opt.isPresent()) 
			return opt.get();
		else
	        throw new IdNotFoundException("ID of the given flight does not exist in the database.");
	}
	public Flight getFlightBySourceAndDestination(String source, String destination) {
		Optional<Flight> opt= flightRepository.getFlightBySourceAndDestination(source, destination);
		if(opt.isPresent()) 
			return opt.get();
		else
	        throw new IdNotFoundException("");
	}
	public List<Flight> getFlightByAirline(String airline) {
		return flightRepository.getFlightByAirline(airline);
	}
	public Flight updateFlight(Flight flight) {
		return flightRepository.save(flight);
	}
	public Optional<Flight> deleteFlight(Integer id) {
		Optional<Flight> opt=flightRepository.findById(id);
		if(opt.isPresent())
			flightRepository.delete(opt.get());
		return opt;
	}
	
}
