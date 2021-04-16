package com.example.bookspace.Output;

import java.time.LocalDateTime;

import com.example.bookspace.models.Publication;


public class PublicationOutput extends OutputManager{ 
    private String self;
    private Long id;
    private String title;
    private String content;
    private LocalDateTime dop;
    private Integer likes;
    private Integer dislikes;
    private Integer n_views;
    private Integer n_likes;
    private Integer n_comments;
    private String category; 
    private UserOutput author; 
    private String likedBy;
    private String dislikedBy;
    private String favBy;
    private String comments;
    private String tags;   
    private String mentions;
    


    public PublicationOutput(Publication p) {
        this.id = p.getId();
        this.self = getURL() + "/publications/" + id;
        this.title = p.getTitle();
        this.content = p.getContent();
        this.dop = p.getDop();
        this.likes = p.getLikes();
        this.dislikes = p.getDislikes();
        this.n_views = p.getViews();
        this.n_likes = p.getTotalLikes();
        this.category = p.getCategory().name();
        this.n_comments = p.getComments().size();
        this.author = new UserOutput(p.getAuthor());
        this.category = p.getCategory().name();
        this.likedBy = self + "/likedBy";
        this.dislikedBy = self + "/dislikedBy";
        this.favBy = self + "/favBy";
        this.comments = self + "/comments";
        this.tags = self + "/tags";
        this.mentions = self + "/mentions";

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

    public Integer getN_views() {
        return this.n_views;
    }

    public void setN_views(Integer n_views) {
        this.n_views = n_views;
    }

    public Integer getN_likes() {
        return this.n_likes;
    }

    public void setN_likes(Integer n_likes) {
        this.n_likes = n_likes;
    }

    public Integer getN_comments() {
        return this.n_comments;
    }

    public void setN_comments(Integer n_comments) {
        this.n_comments = n_comments;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public UserOutput getAuthor() {
        return this.author;
    }

    public void setAuthor(UserOutput author) {
        this.author = author;
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

    public String getFavBy() {
        return this.favBy;
    }

    public void setFavBy(String favBy) {
        this.favBy = favBy;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


    public String getMentions() {
        return this.mentions;
    }

    public void setMentions(String mentions) {
        this.mentions = mentions;
    }

    
    


    



    


    
}
