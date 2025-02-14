package com.example.bookspace.controllers;

import java.util.List;

import com.example.bookspace.models.Message;
import com.example.bookspace.services.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/message")

public class MessageController {
    
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping    
	public List<Message> getMessage() {
        return messageService.getMessage();
	}

    @PutMapping
	public Boolean putMessage(@RequestBody Message messageDetails) {
        return messageService.putMessage(messageDetails);
	}

    @DeleteMapping
	public Boolean deleteMessage(@RequestBody Message messageDetails) {
        return messageService.deleteMessage(messageDetails);
	}
}
