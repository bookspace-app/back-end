package com.example.bookspace.controllers;

import java.util.List;

import com.example.bookspace.services.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/chat")

public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping    
	public List<String> getChat() {
        return chatService.getChats(); //restaurar mensaje commit
	}
    
}
