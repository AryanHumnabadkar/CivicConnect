package com.civic.services;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class PaymentServices {
	
	@Value("${razorpay.key_id}")
	private String key;
	
	@Value("${razorpay.key_secret}")
	private String secret;
	
	
	public String createOrder(Integer amount) throws RazorpayException {
		
		RazorpayClient razorpayclnt = new RazorpayClient(key, secret);
		
		JSONObject order_request = new JSONObject()
				.put("amount", (amount*100))
				.put("currency", "INR")
				.put("receipt", "txn_" + System.currentTimeMillis());;
		
		String order = razorpayclnt.orders.create(order_request).toString();
		
		/*
		 * Here Order object contains following fields : 
		 * {
			  "id": "order_DaZlswtdcn9UNV",
			  "entity": "order",
			  "amount": 50000,
			  "amount_paid": 0,
			  "amount_due": 50000,
			  "currency": "INR",
			  "receipt": "Receipt #20",
			  "status": "created",
			  "attempts": 0,
			  "notes": 
			  	{
				    "key1": "value1",
				    "key2": "value2"
			  	},
			  "created_at": 1572502745
			}
			
			out of this need to validate status to created using if-else..
		 */
		
		return order;
		
	}
	
	
	

	
}
