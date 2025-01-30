package com.sarthak.ai_customer_support.controller;

import com.sarthak.ai_customer_support.model.ChatRequest;
import com.sarthak.ai_customer_support.model.ChatResponse;
import com.sarthak.ai_customer_support.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request){
        return chatService.processChat(request);
    }

}
