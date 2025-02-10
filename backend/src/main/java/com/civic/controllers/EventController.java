package com.civic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic.dto.EventPreRegisterDTO;
import com.civic.pojos.Event;
import com.civic.pojos.Permit;
import com.civic.pojos.User;
import com.civic.services.EventService;

@RestController
@RequestMapping("/api/events")
@CrossOrigin
public class EventController {
	
	//dependency - EventService
	@Autowired
	EventService eventService;
	
	//APIs
	
	@PostMapping("/register/{userId}") //later get userId from body only, DTO
	public ResponseEntity<?> preRegisterEvent(@RequestBody EventPreRegisterDTO evntDetails, @PathVariable long userId){
		Event registedEvent = eventService.preRegisterEvent(evntDetails, userId);
		return ResponseEntity.ok(registedEvent);
	}
	
	@GetMapping("/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable long eventId) {
		Event event = eventService.getEventById(eventId);
        return ResponseEntity.ok(event);
    }
	   
    @GetMapping("/{eventId}/user")
    public ResponseEntity<User> getUserByEvent(@PathVariable long eventId) {
        User user = eventService.getUserByEvent(eventId);
        return ResponseEntity.ok(user);
    }
	
    @GetMapping("/{eventId}/permit")
    public ResponseEntity<Permit> getPermitOfEvent(@PathVariable long eventId) {
        Permit permit = eventService.getPermit(eventId);
        return ResponseEntity.ok(permit);
    }
    
    @GetMapping //means get all
    public ResponseEntity<List<Event>> getAllEvents() {
    	System.out.println("Oye got req");
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
    
    @PutMapping("/{eventId}")
    public ResponseEntity<String> updateEventDetails(@PathVariable long eventId) {
    	//		
        return ResponseEntity.ok("updation remaining");
    }
    
    
    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable long eventId) {
        return ResponseEntity.ok(eventService.deleteEvent(eventId));
    }
    
}
