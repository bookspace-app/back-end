package com.example.bookspace.services;

import java.util.List;

import com.example.bookspace.models.Message;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public List<Message> getMessage() {
        return List.of(new Message());
    }

    public Boolean putMessage(Message messageDetails) {
        return null;
        //return BD.instertMessage(messageDetails);
    }

    public Boolean deleteMessage(Message messageDetails) {
        return null;
        //return BD.deleteMessage(messageDetails);
    }
}