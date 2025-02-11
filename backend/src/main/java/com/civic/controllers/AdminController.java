package com.civic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.civic.dto.ApiResponse;
import com.civic.dto.UpdateUserDTO;
import com.civic.pojos.User;
import com.civic.services.AdminService;
import com.civic.services.CitizenService;
import com.civic.services.EventService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin()
public class AdminController {

	// dependecy - AdminService
	@Autowired
	AdminService adminService;
	
	@Autowired
	private CitizenService citizenService;

	@Autowired
	private EventService eventService;

	/*----------------profile APIs ------------------- */
	@GetMapping("/profile/{userId}")
	public ResponseEntity<?> getProfileDetails(@PathVariable long userId){
		return ResponseEntity.ok(citizenService.getProfileDetails(userId));
	}
	
	@GetMapping("/sectors")
	public ResponseEntity<?> getSectors(){
		return ResponseEntity.ok(citizenService.getSectors());
	}
	
	@PutMapping("/profile/{adminId}")
	public ResponseEntity<?> updateProfile(@Valid @RequestBody UpdateUserDTO userDetails, @PathVariable long adminId){
		String msg = citizenService.updateProfile(adminId, userDetails); //also send token to extract id, instead of directly id
		return ResponseEntity.ok(new ApiResponse(msg));
	}

	@DeleteMapping("/profile/{adminId}")
	public ResponseEntity<String> deleteAdminProfile(@PathVariable long adminId) {
		String result = adminService.deleteProfile(adminId);
		return ResponseEntity.ok(result);
	}

	/*----------------APIs to manage users------------------- */

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(adminService.getAllUsers());
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<?> getUserDetails(@PathVariable long userId) {
		try {
			User user = adminService.getUserById(userId);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<String> updateCitizenProfile(@PathVariable long userId, @RequestBody User citizenDetails) {
		String result = adminService.updateCitizenProfile(userId, citizenDetails);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<?> deleteCitizenProfile(@PathVariable long userId) {
		return ResponseEntity.ok(adminService.deleteCitizenProfile(userId));
	}

	/*----------------APIs to manage events------------------- */

	@DeleteMapping("/events/{eventId}")
	public ResponseEntity<?> cancelEvent(@PathVariable long eventId) {
		return ResponseEntity.ok(eventService.deleteEvent(eventId));
	}

}
