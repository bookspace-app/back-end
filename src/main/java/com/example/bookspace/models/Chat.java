package com.example.bookspace.models;

import java.time.LocalDate;
import java.util.Collection;

import com.example.bookspace.enums.Language;

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

    public Chat(LocalDate date, Language language, Publication publication, Collection<User> participants, Collection<Message> record) {
        this.date = date;
        this.language = language;
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


    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
    public void setPublication(Publication publication) {
        this.publication = publication;
    }
    public void setParticipants(Collection<User> participants) {
        this.participants = participants;
    }
    public void setRecord(Collection<Message> record) {
        this.record = record;
    }
}


