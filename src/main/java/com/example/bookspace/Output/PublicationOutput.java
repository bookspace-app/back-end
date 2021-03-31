package com.example.bookspace.Output;

import java.time.LocalDate;

import com.example.bookspace.models.Publication;

public class PublicationOutput extends OutputManager{ 
    private String self = getURL() + "/publications/";
    private Long id;
    private String title;
    private String content;
    private LocalDate dop;
    private UserOutput author;
    private String favusers;
    private String comments;
    private String category; 
    private String tags;
    


    public PublicationOutput(Publication p, UserOutput author) {
        this.id = p.getId();
        this.self = self + id;
        this.title = p.getTitle();
        this.content = p.getContent();
        this.dop = p.getDop();
        this.author = author;
        this.favusers = self + "/favusers";
        this.comments = self + "/comments";
        this.category = p.getCategory().name();
        this.tags = self + "/tags";

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

    public UserOutput getAuthor() {
        return this.author;
    }

    public void setAuthor(UserOutput author) {
        this.author = author;
    }


    public LocalDate getDop() {
        return this.dop;
    }

    public void setDop(LocalDate dop) {
        this.dop = dop;
    }

    

    

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
    

    public String getFavusers() {
        return this.favusers;
    }

    public void setFavusers(String favusers) {
        this.favusers = favusers;
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
