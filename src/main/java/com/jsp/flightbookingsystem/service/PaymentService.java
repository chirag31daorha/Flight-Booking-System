package com.jsp.flightbookingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.flightbookingsystem.dao.BookingDao;
import com.jsp.flightbookingsystem.dao.PaymentDao;
import com.jsp.flightbookingsystem.dto.ResponseStructure;
import com.jsp.flightbookingsystem.entity.Booking;
import com.jsp.flightbookingsystem.entity.ModeOfTransaction;
import com.jsp.flightbookingsystem.entity.Passenger;
import com.jsp.flightbookingsystem.entity.Payment;
import com.jsp.flightbookingsystem.entity.PaymentStatus;
import com.jsp.flightbookingsystem.exception.IdNotFoundException;

@Service
public class PaymentService {
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private BookingDao bookingDao;
	public ResponseEntity<ResponseStructure<Payment>> recordPayment(Payment payment, Integer bookingId){
		Booking booking = bookingDao.getBookingById(bookingId);
        if (booking == null) {
          throw new IdNotFoundException("Booking not found with id: " + bookingId);
        }
        payment.setBooking(booking);
        booking.setPayment(payment);
        Payment savedPayment=paymentDao.recordPayment(payment);
        ResponseStructure<Payment> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Payment saved");
        response.setData(payment);
        return new ResponseEntity<ResponseStructure<Payment>>(HttpStatus.OK);
    }
	public ResponseEntity<ResponseStructure<List<Payment>>> getAll(){
		ResponseStructure<List<Payment>> response=new ResponseStructure<List<Payment>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("List of payment");
		response.setData(paymentDao.getAll());
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Payment>> getById(Integer paymentId){
		ResponseStructure<Payment> response=new ResponseStructure<Payment>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Payment Details for the paymentId : "+paymentId);
		response.setData(paymentDao.geyById(paymentId));
		return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Payment>>> getByStatus(PaymentStatus status){
		ResponseStructure<List<Payment>> response=new ResponseStructure<List<Payment>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Payment fetched succesfully by status : "+status);
		response.setData(paymentDao.getByStatus(status));
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Payment>>> getByModeOfTransaction(ModeOfTransaction tran){
		ResponseStructure<List<Payment>> response=new ResponseStructure<List<Payment>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Payment fetched by mode of transaction ");
		response.setData(paymentDao.getByModeOfTransaction(tran));
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Payment>> getByBooking(Integer bookingId){
		ResponseStructure<Payment> response=new ResponseStructure<Payment>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Payment by booking");
		response.setData(paymentDao.getByBooking(bookingId));
		return new ResponseEntity<ResponseStructure<Payment>>(response,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(Integer paymentId, PaymentStatus status){
		Payment payment=paymentDao.geyById(paymentId);
		if(payment==null) {
			throw new IdNotFoundException("Payment not found by this Id : "+paymentId);
		}
		payment.setPaymentStatus(status);
		Payment updated= paymentDao.recordPayment(payment);
		ResponseStructure<Payment> response=new ResponseStructure<Payment>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("payment status updated successfully");
		response.setData(updated);
		return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Payment>>> getByAmountGreaterThan(Double amount){
		List<Payment> list=paymentDao.getByAmountGreaterThan(amount);
		if(list.isEmpty()) {
			throw new IdNotFoundException("No payments found above : "+amount);
		}
		ResponseStructure<List<Payment>> response=new ResponseStructure<List<Payment>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Payments above amount : "+ amount +"fetched successfully");
		response.setData(list);
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
	}
}

