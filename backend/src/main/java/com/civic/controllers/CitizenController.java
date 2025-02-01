package com.civic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic.pojos.User;
import com.civic.services.CitizenService;

@RestController
@RequestMapping("/api/citizen")
public class CitizenController {
	//
	@Autowired
	CitizenService citizenService;
	
	//methods
	
	//get 
	@GetMapping("/profile/{userId}")
	public ResponseEntity<?> getProfileDetails(@PathVariable long userId){
		//here also get header, check if both userId's are same then only send data of current user only
		return ResponseEntity.ok(citizenService.getProfileDetails(userId));
	}
	
	@PutMapping("/profile")
	public ResponseEntity<?> updateProfile(@RequestBody User userDetails){
		//here also get header, check if both userId's are same then only send data of current user only
		String msg = citizenService.updateProfile(userDetails);
		return ResponseEntity.ok(msg);
	}
	
	@DeleteMapping("/profile/{userId}")
	public ResponseEntity<?> deleteProfile(@PathVariable long userId){
		//here also get header, check if both userId's are same then only send data of current user only
		String msg = citizenService.deleteProfile(userId);
		return ResponseEntity.ok(msg);
	}
	
}
