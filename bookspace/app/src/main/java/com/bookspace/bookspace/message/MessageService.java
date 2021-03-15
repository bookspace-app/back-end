package com.bookspace.bookspace.message;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public List<Message> getMessage() {
        return List.of(new Message());
    }

    public Boolean putMessage(Message messageDetails) {
        //return BD.instertMessage(messageDetails);
    }

    public Boolean deleteMessage(Message messageDetails) {
        //return BD.deleteMessage(messageDetails);
    }
}