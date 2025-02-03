package com.civic.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
	//msg n timestamp
	
	private String message;
	private LocalDateTime timestamp;
	
	public ApiResponse(String message) {
		super();
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}
	
	
	
	
}
