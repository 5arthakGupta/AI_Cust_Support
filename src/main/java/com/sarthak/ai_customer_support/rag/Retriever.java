package com.sarthak.ai_customer_support.rag;

import com.sarthak.ai_customer_support.model.Document;
import com.sarthak.ai_customer_support.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Retriever {
    private final DocumentRepository documentRepository;

    @Autowired
    public Retriever(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public String retrieveDocuments(String query) {
        // Fetch relevant documents from DB using keyword search
        List<Document> documents = documentRepository.findByContentContainingIgnoreCase(query);

        // Combine document content into a single context string
        return documents.stream()
                .map(Document::getContent)
                .collect(Collectors.joining(" "));
    }
}
