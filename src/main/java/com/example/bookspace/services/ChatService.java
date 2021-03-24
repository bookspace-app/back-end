package com.example.bookspace.services;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ChatService {
    
    public List<String> getChats() {
        return List.of("Hello", "chats");
   
    }
}
