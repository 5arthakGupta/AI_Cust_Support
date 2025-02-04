package com.sarthak.ai_customer_support.rag;

import com.sarthak.ai_customer_support.config.OpenAIConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class Generator {
    private final RestTemplate restTemplate;
    private final String openAiApiKey;
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    @Autowired
    public Generator(OpenAIConfig openAIConfig, RestTemplate restTemplate) {
        this.openAiApiKey = openAIConfig.getOpenAiApiKey();
        this.restTemplate = restTemplate;
    }

    public String generateResponse(String userMessage, String retrievedContext) {
        String prompt = "User query: " + userMessage + "\nRelevant info: " + retrievedContext;

        // Constructing the request payload
        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4-turbo",  // Change to "gpt-3.5-turbo" if needed
                "messages", List.of(
                        Map.of("role", "system", "content", "You are an AI assistant for customer support."),
                        Map.of("role", "user", "content", prompt)
                ),
                "max_tokens", 300
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAiApiKey);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, requestEntity, Map.class);

        // Extracting AI response
        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null && responseBody.containsKey("choices")) {
            return ((Map<String, Object>) ((List<?>) responseBody.get("choices")).get(0)).get("message").toString();
        } else {
            return "Error: No response from OpenAI.";
        }
    }
}
