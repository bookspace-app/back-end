package com.bookspace.bookspace.chat;

import java.time.LocalDate;


public class Chat {

    enum language {
        INGLES, 
        ESPAÑOL, 
        CATALAN
    }
    private LocalDate date;
    // private Publication publication;
    // private Collecion<User> participants;
    // private Collection<Message> record;

    public Chat() {};


    public Chat(LocalDate date) {
        this.date = date;
    }

    // public Chat(Date date, Publication publication, Collecion<User> participants, Collection<Message> record) {
    //     this.date = date;
    //     this.publication = publication;
    //     this.participants = participants;
    //     this.record = record;
    // }  
    

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // public Publication getPublication() {
    //     return this.publication;
    // }


    // public Collecion<User> getParticipants() {
    //     return this.participants;
    // }


    // public Collection<Message> getRecord() {
    //     return this.record;
    // }

}
