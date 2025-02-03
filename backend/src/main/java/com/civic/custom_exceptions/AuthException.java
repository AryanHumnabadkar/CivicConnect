package com.civic.custom_exceptions;

public class AuthException extends RuntimeException {
	public AuthException(String mesg) {
		super(mesg);
	}
}
