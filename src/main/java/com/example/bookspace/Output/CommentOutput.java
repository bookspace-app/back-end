package com.example.bookspace.Output;

import java.time.LocalDate;

import com.example.bookspace.models.Comment;

public class CommentOutput extends OutputManager{
    private String self = getURL() + "/comments/";
    private Long id;
    private String content;
    private LocalDate dop;
    private Long likes;
    private Integer nreplies;
    private String publication;
    private UserOutput author;
    private String replies;


    public CommentOutput(Comment c) {
        this.id = c.getId();
        this.self = self + this.id;;
        this.content = c.getContent();
        this.dop = c.getDop();
        this.likes = c.getLikes();
        this.nreplies = c.getAnswers().size();
        this.publication = new PublicationOutput(c.getPublication()).getSelf();
        this.author = new UserOutput(c.getAuthor());
        this.replies = self + "/replies";
    }


    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDop() {
        return this.dop;
    }

    public void setDop(LocalDate dop) {
        this.dop = dop;
    }

    public Long getLikes() {
        return this.likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }


    public Integer getNreplies() {
        return this.nreplies;
    }

    public void setNreplies(Integer nreplies) {
        this.nreplies = nreplies;
    }

    public String getPublication() {
        return this.publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }


    public UserOutput getAuthor() {
        return this.author;
    }

    public void setAuthor(UserOutput author) {
        this.author = author;
    }

    public String getReplies() {
        return this.replies;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }


    
}
