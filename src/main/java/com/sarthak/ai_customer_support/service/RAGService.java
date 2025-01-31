package com.sarthak.ai_customer_support.service;

import com.sarthak.ai_customer_support.model.ChatRequest;
import com.sarthak.ai_customer_support.model.ChatResponse;
import com.sarthak.ai_customer_support.rag.Generator;
import com.sarthak.ai_customer_support.rag.Retriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RAGService {
    private final Retriever retriever;
    private final Generator generator;

    @Autowired
    public RAGService(Retriever retriever, Generator generator) {
        this.retriever = retriever;
        this.generator = generator;
    }

    public ChatResponse generateResponse(ChatRequest request) {
        // Retrieves relevant documents based on user query
        String relevantContext = retriever.retrieveDocuments(request.getMessage());

        // Generates response using AI model with retrieved context
        String aiGeneratedResponse = generator.generateResponse(request.getMessage(), relevantContext);

        return new ChatResponse(aiGeneratedResponse);
    }
}
