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
import com.civic.dto.RegisterEventResponseDTO;
import com.civic.dto.UpdatePermitRequestDTO;
import com.civic.dto.UpdatePermitResponseDTO;
import com.civic.pojos.Event;
import com.civic.pojos.User;
import com.civic.services.EventService;

@RestController
@RequestMapping("/api/events")
@CrossOrigin()
public class EventController {

    // dependency - EventService
    @Autowired
    EventService eventService;

    // APIs

    @PostMapping("/register/{userId}") // later get userId from body only, DTO
    public ResponseEntity<?> preRegisterEvent(@RequestBody EventPreRegisterDTO evntDetails, @PathVariable long userId) {
        RegisterEventResponseDTO registedEvent = eventService.preRegisterEvent(evntDetails, userId);
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

    @GetMapping // means get all
    public ResponseEntity<List<UpdatePermitResponseDTO>> getAllEvents() {
        System.out.println("Oye got req");
        List<UpdatePermitResponseDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<?> updateEventDetails(@PathVariable long eventId,
            @RequestBody UpdatePermitRequestDTO permitDeatils) {
        return ResponseEntity.ok(eventService.updatePermitStatus(eventId, permitDeatils));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable long eventId) {
        return ResponseEntity.ok(eventService.deleteEvent(eventId));
    }

}
