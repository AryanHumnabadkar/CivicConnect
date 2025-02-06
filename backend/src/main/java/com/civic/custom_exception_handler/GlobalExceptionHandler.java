package com.civic.custom_exception_handler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.civic.custom_exceptions.AuthException;
import com.civic.custom_exceptions.ResourceNotFoundException;
import com.civic.dto.ApiResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {
	//handles all exceptions
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		System.out.println("in method arg invalid " + e);
		List<FieldError> fieldErrors = e.getFieldErrors();// list of fiels having validation errs
		Map<String, String> map = fieldErrors.stream()
				.collect
				(Collectors.toMap
						(FieldError::getField, FieldError::getDefaultMessage));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(map);
	}
	
	
	@ExceptionHandler(AuthException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ApiResponse handleAuthException(AuthException e) {
		System.out.println("in catch-all " + e);
		return new ApiResponse(e.getMessage());
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiResponse handleResourceNotFoundException(ResourceNotFoundException e) {
		System.out.println("in catch-all " + e);
		return new ApiResponse(e.getMessage());
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiResponse UsernameNotFoundException(UsernameNotFoundException e) {
		System.out.println("in catch-all " + e);
		return new ApiResponse(e.getMessage());
	}
}
