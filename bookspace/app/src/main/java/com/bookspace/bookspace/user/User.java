package com.bookspace.bookspace.user;

import java.time.LocalDate;
import java.util.Collection;
import com.bookspace.bookspace.chat.Chat;
import com.bookspace.bookspace.comment.Comment;
import com.bookspace.bookspace.enums.Rank;
import com.bookspace.bookspace.message.Message;
import com.bookspace.bookspace.publication.Publication;



public class User {

    private String email; 
    private String name;
    private LocalDate dov;
    private Rank rank; 
    private LocalDate register; 

    private Collection<Publication> publications;
    private Collection<Publication> voted_publications;
    private Collection<Comment> comments;
    private Collection<Comment> voted_comments;
    private Collection<Message> messages;
    private Collection<Chat> chats;


    public User() {
    }
    

    public User(String email, String name, LocalDate dov, Rank rank, LocalDate register) {
        this.email = email;
        this.name = name;
        this.dov = dov;
        this.rank = rank;
        this.register = register;
    }


    public User(String email, String name, LocalDate dov, Rank rank, LocalDate register, Collection<Publication> publications, Collection<Publication> voted_publications, Collection<Comment> comments, Collection<Comment> voted_comments, Collection<Message> messages, Collection<Chat> chats) {
        this.email = email;
        this.name = name;
        this.dov = dov;
        this.rank = rank;
        this.register = register;
        this.publications = publications;
        this.voted_publications = voted_publications;
        this.comments = comments;
        this.voted_comments = voted_comments;
        this.messages = messages;
        this.chats = chats;
    }


    public String getEmail() {
        return this.email;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDov() {
        return this.dov;
    }

    public void setDov(LocalDate dov) {
        this.dov = dov;
    }

    public Rank getRank() {
        return this.rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public LocalDate getRegister() {
        return this.register;
    }

    public void setRegister(LocalDate register) {
        this.register = register;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Publication> getPublications() {
        return this.publications;
    }

    public void setPublications(Collection<Publication> publications) {
        this.publications = publications;
    }

    public Collection<Publication> getVoted_publications() {
        return this.voted_publications;
    }

    public void setVoted_publications(Collection<Publication> voted_publications) {
        this.voted_publications = voted_publications;
    }

    public Collection<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Collection<Comment> getVoted_comments() {
        return this.voted_comments;
    }

    public void setVoted_comments(Collection<Comment> voted_comments) {
        this.voted_comments = voted_comments;
    }

    public Collection<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public Collection<Chat> getChats() {
        return this.chats;
    }

    public void setChats(Collection<Chat> chats) {
        this.chats = chats;
    }

    

}
