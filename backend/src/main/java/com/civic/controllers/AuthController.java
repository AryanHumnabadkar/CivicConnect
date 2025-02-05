package com.civic.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.civic.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin()
public class AuthController {

	//dependecy - authService
	@Autowired
	private AuthService authService;
	
	//apis
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO userDetails){  //here RegisterUserDTO
		return ResponseEntity.ok(new ApiResponse(authService.registerUser(userDetails)));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginReqDTO loginData){	//here LoginRequest DTO
		LoginRespDTO loginResponse = authService.login(loginData.getEmail(), loginData.getPassword()); 
		return ResponseEntity.status(200).body(loginResponse);	//ret jwt token here
	}
	
	@PostMapping("/logout/{userId}")
	public ResponseEntity<?> logout(@PathVariable Long userId){  
		String logoutmsg = authService.logout(userId); //get token here, verify in service
		return ResponseEntity.ok(new ApiResponse(logoutmsg));
	}
		
	

}
