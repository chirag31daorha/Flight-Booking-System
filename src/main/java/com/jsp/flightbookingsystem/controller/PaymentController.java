package com.jsp.flightbookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.flightbookingsystem.dto.ResponseStructure;
import com.jsp.flightbookingsystem.entity.ModeOfTransaction;
import com.jsp.flightbookingsystem.entity.Payment;
import com.jsp.flightbookingsystem.entity.PaymentStatus;
import com.jsp.flightbookingsystem.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	@PostMapping("/record")
	public ResponseEntity<ResponseStructure<Payment>> recordPayment(@RequestBody Payment payment, @RequestParam Integer bookingId){
		return paymentService.recordPayment(payment, bookingId);
	}
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<List<Payment>>> getAll(){
		return paymentService.getAll();
	}
	@GetMapping("/getById/{paymentId}")
	public ResponseEntity<ResponseStructure<Payment>> getById(@PathVariable Integer paymentId){
		return paymentService.getById(paymentId);
	}
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getByStatus(@PathVariable PaymentStatus status){
		return paymentService.getByStatus(status);
	}
	@GetMapping("/{transaction}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getByModeOfTransaction(@PathVariable ModeOfTransaction tran){
		return paymentService.getByModeOfTransaction(tran);
	}
	@GetMapping("/getByBooking/{bookingId}")
	public ResponseEntity<ResponseStructure<Payment>> getByBooking(@PathVariable Integer bookingId){
		return paymentService.getByBooking(bookingId);
	}
	@PutMapping("/{paymentId}/{status}")
	public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(@PathVariable Integer paymentId, @PathVariable PaymentStatus status){
		return paymentService.updatePaymentStatus(paymentId, status);
	}
	@GetMapping("/amount/{amount}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getByAmountGreaterThan(@PathVariable Double amount){
		return paymentService.getByAmountGreaterThan(amount);
	}
}
