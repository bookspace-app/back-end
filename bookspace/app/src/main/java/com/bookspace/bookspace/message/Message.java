package com.bookspace.bookspace.message;

import java.time.LocalDate;

import com.bookspace.bookspace.chat.Chat;
import com.bookspace.bookspace.user.User;

public class Message {

    private String content;
    private LocalDate date; 

    private Chat chat;
    private User owner;

    public Message() {
    }

    public Message(String content, LocalDate date, Chat chat, User owner) {
        this.content = content;
        this.date = date;
        this.chat = chat;
        this.owner = owner;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Chat getChat() {
        return this.chat;
    }


    public User getOwner() {
        return this.owner;
    }
}
