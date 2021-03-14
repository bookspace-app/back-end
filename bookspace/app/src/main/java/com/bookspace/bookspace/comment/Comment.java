package com.bookspace.bookspace.comment;

import java.time.LocalDate;

public class Comment {
    
    private String content;
    private LocalDate date;

    // private User user;
    // private Collection<User> votedBy;
    // private Publication publication;
    // private Comment parent;
    // private Collection<Comment> answers;


    public Comment() {
        this.content = "";
        this.date = LocalDate.now(); 
    }

    public Comment(String content, LocalDate date) {
        this.content = content;
        this.date = date;
    }


    // public Comment(String content, LocalDate date, User user, Collection<User> votedBy, Publication publication, Comment parent, Collection<Comment> answers) {
    //     this.content = content;
    //     this.date = date;
    //     this.user = user;
    //     this.votedBy = votedBy;
    //     this.publication = publication;
    //     this.parent = parent;
    //     this.answers = answers;
    // }

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

    // public User getUser() {
    //     return this.user;
    // }


    // public Collection<User> getVotedBy() {
    //     return this.votedBy;
    // }


    // public Publication getPublication() {
    //     return this.publication;
    // }


    // public Comment getParent() {
    //     return this.parent;
    // }


    // public Collection<Comment> getAnswers() {
    //     return this.answers;
    // }


    
}
