package com.civic.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// send error message : SC 401
		System.out.println("------imma here");
		// Set the response status code
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // Set the response content type
        response.setContentType("application/json");
        // Write the error message to the response body
        response.getWriter().write("{\"error\": \"" + authException.getMessage() + "\"}");

	}

}
