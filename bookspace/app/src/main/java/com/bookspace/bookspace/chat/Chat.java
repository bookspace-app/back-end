package com.bookspace.bookspace.chat;

import java.time.LocalDate;
import java.util.Collection;

import com.bookspace.bookspace.enums.Language;
import com.bookspace.bookspace.message.Message;
import com.bookspace.bookspace.publication.Publication;
import com.bookspace.bookspace.user.User;


public class Chat {

   
    private LocalDate date;
    private Language language; 
    private Publication publication;
    private Collection<User> participants;
    private Collection<Message> record;

    public Chat() {};


    public Chat(LocalDate date) {
        this.date = date;
    }

    public Chat(LocalDate date, Publication publication, Collection<User> participants, Collection<Message> record) {
        this.date = date;
        this.publication = publication;
        this.participants = participants;
        this.record = record;
    }  
    

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Publication getPublication() {
        return this.publication;
    }


    public Collection<User> getParticipants() {
        return this.participants;
    }


    public Collection<Message> getRecord() {
        return this.record;
    }

}
