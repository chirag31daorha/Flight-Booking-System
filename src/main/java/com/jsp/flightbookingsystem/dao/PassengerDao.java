package com.jsp.flightbookingsystem.dao;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jsp.flightbookingsystem.entity.Passenger;
import com.jsp.flightbookingsystem.exception.IdNotFoundException;
import com.jsp.flightbookingsystem.repository.PassengerRepository;
@Repository
public class PassengerDao {
	@Autowired
	private PassengerRepository passengerRepository;
	public Passenger addPassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
	}
	public List<Passenger> getAll(){
		return passengerRepository.findAll();
	}
	public Passenger getPassengerById(Integer id) {
		Optional<Passenger> opt= passengerRepository.findById(id);
		if(opt.isPresent())
			return opt.get();
		else
			throw new IdNotFoundException("Id is invalid");
	}
	public Passenger getPassengerByContactNumber(String contactNumber) {
		return passengerRepository.getPassengerByContactNumber(contactNumber);
	}
	public Passenger updatePassenger(Passenger passenger) {
		Optional<Passenger> opt=passengerRepository.findById(passenger.getId());
		if(opt.isPresent())
			return passengerRepository.save(passenger);
		else
			throw new IdNotFoundException("Id is not valid");
	}
	public List<Passenger> getByFlight(Integer flightId) {
		return passengerRepository.findByBookingFlightId(flightId);
	}
}