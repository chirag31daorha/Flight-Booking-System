package com.jsp.flightbookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.flightbookingsystem.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>{
	Passenger getPassengerByContactNumber(String contactNumber);
	List<Passenger> findByBookingFlightId(Integer Id);
	
}
