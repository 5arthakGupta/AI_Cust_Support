package com.sarthak.ai_customer_support.rag;

import org.springframework.stereotype.Component;

@Component
public class Generator {
    public String generateResponse(String query, String context) {
        // Placeholder logic: Simulate AI-generated response
        return "Based on your query ('" + query + "'), here is the response: " + context;
    }
}
