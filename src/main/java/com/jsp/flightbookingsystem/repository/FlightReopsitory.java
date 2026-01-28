package com.jsp.flightbookingsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.flightbookingsystem.entity.Flight;

public interface FlightReopsitory extends JpaRepository<Flight, Integer> {
	Optional<Flight> getFlightBySourceAndDestination(String source, String destination);
	List<Flight> getFlightByAirline(String airline);
}
