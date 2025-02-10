package com.civic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic.services.PaymentServices;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	@Autowired
	private PaymentServices paymentService;
	
	@PostMapping("/create-order")
	public ResponseEntity<String> createOrder(@RequestBody Integer amount ) throws RazorpayException {
		
		String order = paymentService.createOrder(amount);
		
		return ResponseEntity.ok(order);
		
	}
	
	

}
