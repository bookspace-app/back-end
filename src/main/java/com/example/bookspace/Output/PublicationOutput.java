package com.example.bookspace.Output;

import java.time.LocalDate;

import com.example.bookspace.models.Publication;

public class PublicationOutput extends OutputManager{ 
    private String self = getURL() + "/publications/";
    private Long id;
    private String title;
    private String content;
    private LocalDate dop;
    private Long views;
    private Long likes;
    private String category; 
    private UserOutput author; 
    private String votedUsers;
    private String favUsers;
    private String comments;
    private String tags;
    


    public PublicationOutput(Publication p) {
        this.id = p.getId();
        this.self = self + id;
        this.title = p.getTitle();
        this.content = p.getContent();
        this.dop = p.getDop();
        this.views = p.getViews();
        this.author = new UserOutput(p.getAuthor());
        this.votedUsers = self + "/votedUsers";
        this.favUsers = self + "/favUsers";
        this.comments = self + "/comments";
        this.category = p.getCategory().name();
        this.tags = self + "/tags";
        this.likes = p.getLikes();

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

    public LocalDate getDop() {
        return this.dop;
    }

    public void setDop(LocalDate dop) {
        this.dop = dop;
    }

    public Long getViews() {
        return this.views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getLikes() {
        return this.likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
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
