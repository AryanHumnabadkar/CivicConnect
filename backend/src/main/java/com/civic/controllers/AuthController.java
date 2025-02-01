package com.civic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic.pojos.User;
import com.civic.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	//dependecy - authService
	@Autowired
	private AuthService authService;
	
	//apis
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		String isUserCreated = authService.registerUser(user);
		return ResponseEntity.ok(isUserCreated);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User loginData){
		User loggedUser = authService.login(loginData.getEmail(), loginData.getPassword());
		return ResponseEntity.ok(loggedUser);
	}
	
	@PostMapping("/logout/{userId}")
	public ResponseEntity<?> logout(@PathVariable Long userId){
		String logoutmsg = authService.logout(userId);
		return ResponseEntity.ok(logoutmsg);
	}
		
	
	
}
