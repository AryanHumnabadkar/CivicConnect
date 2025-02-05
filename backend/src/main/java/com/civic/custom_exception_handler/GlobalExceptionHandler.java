package com.civic.custom_exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.civic.custom_exceptions.AuthException;
import com.civic.custom_exceptions.ResourceNotFoundException;
import com.civic.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	//handles all exceptions
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ApiResponse handleAuthException(AuthException e) {
		System.out.println("in catch-all " + e);
		return new ApiResponse(e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiResponse handleResourceNotFoundException(ResourceNotFoundException e) {
		System.out.println("in catch-all " + e);
		return new ApiResponse(e.getMessage());
	}
}
