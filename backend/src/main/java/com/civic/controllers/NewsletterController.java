package com.civic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic.dto.NewsletterRequest;
import com.civic.services.NewsletterService;

@RestController
@RequestMapping("/api/newsletter")
@CrossOrigin
public class NewsletterController {

    @Autowired
    private NewsletterService newsletterService;

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody NewsletterRequest request) {
        String response = newsletterService.subscribeUser(request.getEmail(), request.getCategory());
        return ResponseEntity.ok("Subscription Successful: " + request.getCategory());
    }
}
