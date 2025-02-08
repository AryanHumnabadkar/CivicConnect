package com.civic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic.dto.ApiResponse;
import com.civic.dto.LoginReqDTO;
import com.civic.dto.LoginRespDTO;
import com.civic.dto.RegisterUserDTO;
import com.civic.pojos.User;
import com.civic.security.JwtUtils;
import com.civic.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin()
@Validated
public class AuthController {

	// dependency - authService, AuthenticationManager, JwtUtils
	@Autowired
	private AuthService authService;

	// apis

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO userDetails) {
		return ResponseEntity.ok(new ApiResponse(authService.registerUser(userDetails)));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginReqDTO loginData) {
		// TODO: emailOrPassword exception?
		return ResponseEntity.status(200).body(authService.login(loginData.getEmail(), loginData.getPassword())); // ret
																													// jwt
																													// token
																													// here
	}

	@PostMapping("/logout/{userId}")
	public ResponseEntity<?> logout(@PathVariable Long userId) {
		// No server-side invalidation needed. Invalidate on frontend only
		return ResponseEntity.ok(new ApiResponse(authService.logout()));
	}

}
