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
 
	//dependecy - authService
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	//JwtUtils
	@Autowired
	private JwtUtils jwtUtils;
	
	//apis
	
	@PostMapping("/register") 
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO userDetails){  
		return ResponseEntity.ok(new ApiResponse(authService.registerUser(userDetails)));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginReqDTO loginData){	
		//first
	    Authentication verifiedAuth = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginData.getEmail(), loginData.getPassword()));		
		return ResponseEntity.status(200).body(new ApiResponse(jwtUtils.generateToken(verifiedAuth)));	//ret jwt token here
	}
	
<<<<<<< Updated upstream
	@PostMapping("/logout/{userId}")
	public ResponseEntity<?> logout(@PathVariable Long userId){ 
		//get token here and invalidate
		String logoutmsg = authService.logout(userId); 
		return ResponseEntity.ok(new ApiResponse(logoutmsg));
=======
	@PostMapping("/logout")
	public ResponseEntity<?> logout(){ 
	    // No server-side invalidation needed. Invalidate on frontend only
		System.out.println("------------------------------");
		return ResponseEntity.ok(new ApiResponse(authService.logout()));
>>>>>>> Stashed changes
	}
		
	

}
