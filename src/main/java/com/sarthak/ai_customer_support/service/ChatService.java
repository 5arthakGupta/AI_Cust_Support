package com.sarthak.ai_customer_support.service;

import com.sarthak.ai_customer_support.model.ChatRequest;
import com.sarthak.ai_customer_support.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    public ChatResponse processChat(ChatRequest request) {
        // Placeholder logic for processing chat request
        String responseMessage = "You said: " + request.getMessage();
        return new ChatResponse(responseMessage);
    }
}
