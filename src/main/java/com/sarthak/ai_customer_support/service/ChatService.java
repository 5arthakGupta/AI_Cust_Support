package com.sarthak.ai_customer_support.service;

import com.sarthak.ai_customer_support.model.ChatRequest;
import com.sarthak.ai_customer_support.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final RAGService ragService;

    @Autowired
    public ChatService(RAGService ragService) {
        this.ragService = ragService;
    }

    public ChatResponse processChat(ChatRequest request) {
        return ragService.generateResponse(request);
    }
}
