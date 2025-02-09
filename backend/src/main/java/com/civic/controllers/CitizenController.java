package com.civic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic.dto.ApiResponse;
import com.civic.dto.UpdateUserDTO;
import com.civic.pojos.User;
import com.civic.services.CitizenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/citizen")
@Validated
@CrossOrigin()
public class CitizenController {
	//
	@Autowired
	CitizenService citizenService;
	
	//methods
	
	@GetMapping("/profile/{userId}")
	public ResponseEntity<?> getProfileDetails(@PathVariable long userId){
		return ResponseEntity.ok(citizenService.getProfileDetails(userId));
	}
	
	@GetMapping("/sectors")
	public ResponseEntity<?> getSectors(){
		return ResponseEntity.ok(citizenService.getSectors());
	}
	
	@PutMapping("/profile/{userId}")
	public ResponseEntity<?> updateProfile(@Valid @RequestBody UpdateUserDTO userDetails, @PathVariable long userId){
		String msg = citizenService.updateProfile(userId, userDetails); //also send token to extract id, instead of directly id
		return ResponseEntity.ok(new ApiResponse(msg));
	}
	
	@DeleteMapping("/profile/{userId}")
	public ResponseEntity<?> deleteProfile(@PathVariable long userId){
		String msg = citizenService.deleteProfile(userId);
		return ResponseEntity.ok(new ApiResponse(msg));
	}
	
}
