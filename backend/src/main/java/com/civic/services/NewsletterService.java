package com.civic.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NewsletterService {
    private static final String API_KEY = "d62d10da846fd8c7ba119ed7d787407c-us14";
    private static final String LIST_ID = "51df8c62a9";
    private static final String DC = "us14"; // Replace with your Mailchimp data center (found in API Key)

    public String subscribeUser(String email, String category) {
        WebClient webClient = WebClient.builder()
            .baseUrl("https://" + DC + ".api.mailchimp.com/3.0")
            .defaultHeader("Authorization", "Basic " + API_KEY)
            .build();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email_address", email);
        requestBody.put("status", "subscribed"); // Can be "subscribed" or "pending"
        requestBody.put("tags", List.of(category));
        
        return webClient.post()
            .uri("/lists/" + LIST_ID + "/members")
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }
}
