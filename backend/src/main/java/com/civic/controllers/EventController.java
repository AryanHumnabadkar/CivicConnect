package com.civic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic.pojos.Event;
import com.civic.pojos.Permit;
import com.civic.pojos.User;
import com.civic.services.EventService;

@RestController
@RequestMapping("/api/events")
public class EventController {
	
	//dependecny - EventService
	@Autowired
	EventService eventService;
	
	//apis
	
	@PostMapping("/register/{userId}")
	public ResponseEntity<?> registerEvent(@RequestBody Event evntDetails, @PathVariable long userId){
		Event registedEvent = eventService.registerEvent(evntDetails, userId);
		return ResponseEntity.ok(registedEvent);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable long id) {
		Event event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }
	
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
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
    
    @DeleteMapping("/{evenId}")
    public ResponseEntity<?> deleteEvent(@PathVariable long eventId) {
        return ResponseEntity.ok(eventService.deleteEvent(eventId));
    }
    
}
