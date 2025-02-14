package com.example.bookspace.models;

import java.time.LocalDate;

public class Message {

    private String content;
    private LocalDate date; 

    private Chat chat;
    private User author;

    public Message() {
    }

    public Message(String content, LocalDate date, Chat chat, User author) {
        this.content = content;
        this.date = date;
        this.chat = chat;
        this.author = author;
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


    public User getAuthor() {
        return this.author;
    }
}
