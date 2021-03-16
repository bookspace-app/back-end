package com.bookspace.bookspace.publication;

import java.time.LocalDate;
import java.util.Collection;

import com.bookspace.bookspace.chat.Chat;
import com.bookspace.bookspace.comment.Comment;
import com.bookspace.bookspace.theme.Theme;
import com.bookspace.bookspace.user.User;


public class Publication {

    private String title;
    private String content;
    private LocalDate date; 

    private User owner;
    private Collection<User> votedBy;
    private Collection<Comment> comments;
    private Theme theme;
    private Collection<Chat> chats;



    public Publication() {
    }

    public Publication(String title, String content, LocalDate date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public Publication(String title, String content, LocalDate date, User owner, Collection<User> votedBy, Collection<Comment> comments, Theme theme, Collection<Chat> chats) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.owner = owner;
        this.votedBy = votedBy;
        this.comments = comments;
        this.theme = theme;
        this.chats = chats;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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



    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Collection<User> getVotedBy() {
        return this.votedBy;
    }

    public void setVotedBy(Collection<User> votedBy) {
        this.votedBy = votedBy;
    }

    public Collection<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Theme getTheme() {
        return this.theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Collection<Chat> getChats() {
        return this.chats;
    }

    public void setChats(Collection<Chat> chats) {
        this.chats = chats;
    }
    
}
