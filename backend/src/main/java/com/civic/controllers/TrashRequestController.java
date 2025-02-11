package com.civic.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic.dto.CreateTrashReqDTO;
import com.civic.dto.TrashReqDTO;
import com.civic.dto.UpdateTrashReqDTO;
import com.civic.pojos.TrashRequest;
import com.civic.services.TrashRequestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin()
@Validated
public class TrashRequestController {
	
	@Autowired
	private TrashRequestService trashService;
	
	@GetMapping
    public ResponseEntity<List<TrashReqDTO>> getAllTrashRequests() {
        List<TrashReqDTO> trashRequests = trashService.getAllTrashRequests();
        return ResponseEntity.ok(trashRequests);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<TrashReqDTO> getTrashRequestById(@PathVariable Long requestId) {
    	TrashReqDTO trashRequest = trashService.getTrashRequestById(requestId);
        return ResponseEntity.ok(trashRequest);
    }

    @PostMapping
    public ResponseEntity<String> createTrashRequest(@Valid @RequestBody CreateTrashReqDTO trashRequestDto) {
    	String msg = trashService.createTrashRequest(trashRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @PutMapping("/{requestId}")
    public ResponseEntity<TrashReqDTO> updateTrashRequest(@PathVariable Long requestId, @Valid @RequestBody UpdateTrashReqDTO serviceDate) { //make updateTrashReq DTO
    	//To update description only
    	TrashReqDTO updatedTrashRequest = trashService.updateTrashRequest(requestId, serviceDate);
        return ResponseEntity.ok(updatedTrashRequest);
    }

    @DeleteMapping("/{requestId}")
    public ResponseEntity<String> deleteTrashRequest(@PathVariable Long requestId) { 	
        return ResponseEntity.ok(trashService.deleteTrashRequest(requestId));
    }
	
}
