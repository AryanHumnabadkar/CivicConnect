package com.civic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic.dto.PaymentAmountDTO;
import com.civic.services.PaymentServices;
import com.razorpay.RazorpayException;

@CrossOrigin()
@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	@Autowired
	private PaymentServices paymentService;
	
	@PostMapping("/create-order")
	public ResponseEntity<String> createOrder(@RequestBody PaymentAmountDTO paymentAmount ) throws RazorpayException {
	
		System.out.println("Inside payment controller");
		
		Integer amount = paymentAmount.getAmount();
		
		String order = paymentService.createOrder(amount);
		
		return ResponseEntity.ok(order);
		
	}
	
	

}
