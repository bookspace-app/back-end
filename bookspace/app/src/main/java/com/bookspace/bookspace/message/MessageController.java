package com.bookspace.bookspace.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/message")

public class MessageController {
    
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping    
	public String getMessage() {
        return "MS";
	}

    @PutMapping    
	public String putMessage() {
        return "MS";
	}

    @DeleteMapping  
	public String deleteMessage() {
        return "MS";
	}
}
