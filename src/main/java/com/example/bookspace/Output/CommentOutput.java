package com.example.bookspace.Output;

import java.time.LocalDateTime;

import com.example.bookspace.models.Comment;

public class CommentOutput extends OutputManager {
    private String selfUri;
    private Long id;
    private String content;
    private LocalDateTime dop;
    private Integer likes;
    private Integer dislikes;
    private Integer totalLikes;
    private UserOutput author;
    private Integer nReplies;
    private String publicationUri;
    private String likedByUri;
    private String dislikedByUri;
    private String answersUri;

    public CommentOutput(){}


    public CommentOutput(Comment c) {
        this.id = c.getId();
        this.content = c.getContent();
        this.dop = c.getDop();
        this.likes = c.getLikedBy().size();
        this.dislikes = c.getDislikedBy().size();
        this.nReplies = c.getAnswers().size();
        this.author = new UserOutput(c.getAuthor());
        this.totalLikes = this.likes-this.dislikes;
        this.selfUri = getURL() + "/comments/" + this.id;
        this.publicationUri = getURL() +  "/publications/" + c.getPublication().getId();
        this.likedByUri = this.selfUri + "/like";
        this.dislikedByUri = this.selfUri + "/dislike";
        this.answersUri = this.selfUri + "answers";
    }



    public String getSelfUri() {
        return this.selfUri;
    }

    public void setSelfUri(String selfUri) {
        this.selfUri = selfUri;
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

    public Integer getTotalLikes() {
        return this.totalLikes;
    }

    public void setTotalLikes(Integer totalLikes) {
        this.totalLikes = totalLikes;
    }

    public UserOutput getAuthor() {
        return this.author;
    }

    public void setAuthor(UserOutput author) {
        this.author = author;
    }

    public Integer getNReplies() {
        return this.nReplies;
    }

    public void setNReplies(Integer nReplies) {
        this.nReplies = nReplies;
    }

    public String getPublicationUri() {
        return this.publicationUri;
    }

    public void setPublicationUri(String publicationUri) {
        this.publicationUri = publicationUri;
    }

    public String getLikedByUri() {
        return this.likedByUri;
    }

    public void setLikedByUri(String likedByUri) {
        this.likedByUri = likedByUri;
    }

    public String getDislikedByUri() {
        return this.dislikedByUri;
    }

    public void setDislikedByUri(String dislikedByUri) {
        this.dislikedByUri = dislikedByUri;
    }

    public String getAnswersUri() {
        return this.answersUri;
    }

    public void setAnswersUri(String answersUri) {
        this.answersUri = answersUri;
    }


    

}
