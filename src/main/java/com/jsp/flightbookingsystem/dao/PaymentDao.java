package com.jsp.flightbookingsystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.flightbookingsystem.entity.ModeOfTransaction;
import com.jsp.flightbookingsystem.entity.Payment;
import com.jsp.flightbookingsystem.entity.PaymentStatus;
import com.jsp.flightbookingsystem.exception.IdNotFoundException;
import com.jsp.flightbookingsystem.repository.PaymentRepository;

@Repository
public class PaymentDao {
	@Autowired
	private PaymentRepository paymentRepository;
	public Payment recordPayment(Payment payment) {
		return paymentRepository.save(payment);
	}
	public List<Payment> getAll(){
		return paymentRepository.findAll();
	}
	public Payment geyById(Integer paymentId) {
		Optional<Payment> opt=paymentRepository.findById(paymentId);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new IdNotFoundException("ID not found for this payment");
		}
	}
	public List<Payment> getByStatus(PaymentStatus status) {
		return paymentRepository.getPaymentByPaymentStatus(status);
	}
	public List<Payment> getByModeOfTransaction(ModeOfTransaction tran){
		return paymentRepository.getByModeOfTransaction(tran);
	}
	public Payment getByBooking(Integer bookingId) {
		return paymentRepository.findByBookingId(bookingId);
	}
	public List<Payment> getByAmountGreaterThan(Double amount) {
		return paymentRepository.findByAmountGreaterThan(amount);
	}
}
