package com.jsp.flightbookingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.flightbookingsystem.dao.FlightDao;
import com.jsp.flightbookingsystem.dto.ResponseStructure;
import com.jsp.flightbookingsystem.entity.Flight;
import com.jsp.flightbookingsystem.exception.IdNotFoundException;


@Service
public class FlightService {
	@Autowired
	private FlightDao flightDao;
	public ResponseEntity<ResponseStructure<Flight>> addFlight(Flight flight){
		ResponseStructure<Flight> response=new ResponseStructure<Flight>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Flight Added Successfully");
		response.setData(flightDao.addFlight(flight));
		return new ResponseEntity<ResponseStructure<Flight>>(response,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<List<Flight>>> getAllFlights(){
		ResponseStructure<List<Flight>> response=new ResponseStructure<List<Flight>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("A Flights List : ");
		response.setData(flightDao.getAllFlights());
		return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Flight>> getFlightById(Integer id){
		ResponseStructure<Flight> response=new ResponseStructure<Flight>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Flight details for the given flight_id");
		response.setData(flightDao.getFlightById(id));
		return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Flight>> getFlightBySourceAndDestination(String source, String destination){
		ResponseStructure<Flight> response=new ResponseStructure<Flight>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Flight details for the given source and destination");
		response.setData(flightDao.getFlightBySourceAndDestination(source, destination));
		return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightByAirline(String airline){
		ResponseStructure<List<Flight>> response=new ResponseStructure<List<Flight>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Flight details for the given airline");
		response.setData(flightDao.getFlightByAirline(airline));
		return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Flight>> updateFlight(Flight flight){
		ResponseStructure<Flight> response=new ResponseStructure<Flight>();
		if(flight.getId()==null) {
			throw new IdNotFoundException("Id need to be mention to update a record");
		}	
		Optional<Flight> opt=Optional.ofNullable(flightDao.getFlightById(flight.getId()));
		if(opt.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
		    response.setMessage("Flight record is updated");
			response.setData(flightDao.updateFlight(flight));
			return new  ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException("Id does not exist in the database");
		}
	}
	public ResponseEntity<ResponseStructure<String>> deleteFlight(Integer id){
		ResponseStructure<String> response=new ResponseStructure<String>();
		Optional<Flight> opt=flightDao.deleteFlight(id);
		if(opt.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book record deleted successfully");
			response.setData("success");
		}
		else {
			throw new IdNotFoundException("Book record with id : "+id+" is not found ");
		}
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
	}
}

