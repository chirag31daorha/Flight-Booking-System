package com.jsp.flightbookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.flightbookingsystem.entity.ModeOfTransaction;
import com.jsp.flightbookingsystem.entity.Payment;
import com.jsp.flightbookingsystem.entity.PaymentStatus;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	List<Payment> getPaymentByPaymentStatus(PaymentStatus status);
	List<Payment> getByModeOfTransaction(ModeOfTransaction tran);
	Payment findByBookingId(Integer bookingId);
	List<Payment> findByAmountGreaterThan(Double Amount);
}
