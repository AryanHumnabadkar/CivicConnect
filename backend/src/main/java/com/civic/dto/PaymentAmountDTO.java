package com.civic.dto;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentAmountDTO {
	
	private Integer amount;
	
	public PaymentAmountDTO(Integer amount) {
		this.amount = amount;
	}
	

}
