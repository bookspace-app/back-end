package com.example.bookspace.Output;

import java.time.LocalDateTime;

import com.example.bookspace.models.Publication;


public class PublicationOutput extends OutputManager{ 
    private String self;
    private Long id;
    private String title;
    private String content;
    private LocalDateTime dop;
    private Integer n_views;
    private Integer n_likes;
    private Integer n_comments;
    private String category; 
    private UserOutput author; 
    private String votedUsers;
    private String favUsers;
    private String comments;
    private String tags;
    
    


    public PublicationOutput(Publication p) {
        this.id = p.getId();
        this.self = getURL() + "/publications/" + id;
        this.title = p.getTitle();
        this.content = p.getContent();
        this.dop = p.getDop();
        this.n_views = p.getViews();
        this.n_likes = p.getLikes();
        this.category = p.getCategory().name();
        this.n_comments = p.getComments().size();
        this.author = new UserOutput(p.getAuthor());
        this.category = p.getCategory().name();
        this.votedUsers = self + "/votedUsers";
        this.favUsers = self + "/favUsers";
        this.comments = self + "/comments";
        this.tags = self + "/tags";

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

    public String getVotedUsers() {
        return this.votedUsers;
    }

    public void setVotedUsers(String votedUsers) {
        this.votedUsers = votedUsers;
    }

    public String getFavUsers() {
        return this.favUsers;
    }

    public void setFavUsers(String favUsers) {
        this.favUsers = favUsers;
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

    

    


    



    


    
}
