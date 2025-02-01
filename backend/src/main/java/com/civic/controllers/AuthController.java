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
	public ResponseEntity<?> registerUser(@RequestBody User user){  //here RegisterUserDTO
		String isUserCreated = authService.registerUser(user); //pass UserDTO only
		return ResponseEntity.ok(isUserCreated);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User loginData){	//here LoginRequest DTO
		User loggedUser = authService.login(loginData.getEmail(), loginData.getPassword()); //pass email and pass from LoginRequest to service layer and there verification
		return ResponseEntity.ok(loggedUser);	//ret jwt token here
	}
	
	@PostMapping("/logout/{userId}")
	public ResponseEntity<?> logout(@PathVariable Long userId){  
		String logoutmsg = authService.logout(userId); //get token here, verify in service
		return ResponseEntity.ok(logoutmsg);
	}
		
	

}
