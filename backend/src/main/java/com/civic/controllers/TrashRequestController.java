package com.civic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic.dto.CreateTrashReqDTO;
import com.civic.pojos.TrashRequest;
import com.civic.services.TrashRequestService;

@RestController
@RequestMapping("/api/requests")
public class TrashRequestController {
	
	@Autowired
	private TrashRequestService trashService;
	
	@GetMapping
    public ResponseEntity<List<TrashRequest>> getAllTrashRequests() {
        List<TrashRequest> trashRequests = trashService.getAllTrashRequests();
        return ResponseEntity.ok(trashRequests);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<TrashRequest> getTrashRequestById(@PathVariable Long requestId) {
    	TrashRequest trashRequest = trashService.getTrashRequestById(requestId);
        return ResponseEntity.ok(trashRequest);
    }

    @PostMapping
    public ResponseEntity<String> createTrashRequest(@RequestBody CreateTrashReqDTO trashRequestDto) {
    	String createdTrashRequest = trashService.createTrashRequest(trashRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrashRequest);
    }

    @PutMapping("/{requestId}")
    public ResponseEntity<TrashRequest> updateTrashRequest(@PathVariable Long requestId, @RequestBody TrashRequest trashRequestDto) {
    	TrashRequest updatedTrashRequest = trashService.updateTrashRequest(requestId, trashRequestDto);
        return ResponseEntity.ok(updatedTrashRequest);
    }

    @DeleteMapping("/{requestId}")
    public ResponseEntity<String> deleteTrashRequest(@PathVariable Long requestId) {
    	
        return ResponseEntity.ok(trashService.deleteTrashRequest(requestId));
    }
	
}
