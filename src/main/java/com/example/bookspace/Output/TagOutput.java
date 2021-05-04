package com.example.bookspace.Output;


import com.example.bookspace.models.Tag;
public class TagOutput extends OutputManager{

    private String self = getURL() + "/tags/";
    private Long id;
    private String name;
    private UserOutput author;
    private String publications;
    private String users;

    public TagOutput() {
        
    }

    public TagOutput(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
        this.author = new UserOutput(tag.getAuthor());
        this.self = self + this.id;
        this.publications = this.self + "/publications";
        this.users = this.self + "/users";
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserOutput getAuthor() {
        return this.author;
    }

    public void setAuthor(UserOutput author) {
        this.author = author;
    }

    public String getPublications() {
        return this.publications;
    }

    public void setPublications(String publications) {
        this.publications = publications;
    }

    public String getUsers() {
        return this.users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
    
    

    

}