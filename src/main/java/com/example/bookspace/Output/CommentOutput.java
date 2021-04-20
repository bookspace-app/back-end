package com.example.bookspace.Output;

import java.time.LocalDateTime;

import com.example.bookspace.models.Comment;

public class CommentOutput extends OutputManager {
    private String self;
    private Long id;
    private String content;
    private LocalDateTime dop;
    private String author;
    private String publication;
    private Integer likes;
    private Integer dislikes;
    private Integer nLikes;
    private String likedBy;
    private String dislikedBy;
    private String answers;


    public CommentOutput(Comment c) {
        this.id = c.getId();
        this.content = c.getContent();
        this.dop = c.getDop();
        this.likes = c.getLikedBy().size();
        this.dislikes = c.getDislikedBy().size();
        this.nLikes = this.likes-this.dislikes;
        this.author = getURL() + "/users/" + c.getAuthor().getId();
        this.publication = getURL() +  "/publications/" + c.getPublication().getId();
        this.self = getURL() + "/comments/" + this.id;
        this.likedBy = this.self + "/like";
        this.dislikedBy = this.self + "/dislike";
        this.answers = this.self + "answers";
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

    public LocalDateTime getDop() {
        return this.dop;
    }

    public void setDop(LocalDateTime dop) {
        this.dop = dop;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublication() {
        return this.publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public Integer getLikes() {
        return this.likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return this.dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public Integer getNLikes() {
        return this.nLikes;
    }

    public void setNLikes(Integer nLikes) {
        this.nLikes = nLikes;
    }

    public String getLikedBy() {
        return this.likedBy;
    }

    public void setLikedBy(String likedBy) {
        this.likedBy = likedBy;
    }

    public String getDislikedBy() {
        return this.dislikedBy;
    }

    public void setDislikedBy(String dislikedBy) {
        this.dislikedBy = dislikedBy;
    }

    public String getAnswers() {
        return this.answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
    

    

}
