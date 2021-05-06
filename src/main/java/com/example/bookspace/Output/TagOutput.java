package com.example.bookspace.Output;

import com.example.bookspace.models.Tag;

//Class for the Output data of the Tags
public class TagOutput extends OutputManager {

    private String self = getURL() + "/tags/";
    private Long id;
    private String name;
    private UserOutput author;
    private String publications;
    private String users;

    //Default constructor
    public TagOutput() {}

    //Constructor given a Tag
    public TagOutput(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
        this.author = new UserOutput(tag.getAuthor());
        this.self = self + this.id;
        this.publications = this.self + "/publications";
        this.users = this.self + "/users";
    }

    //Getter of {self} attribute
    public String getSelf() {
        return this.self;
    }

    //Setter of {self} attribute
    public void setSelf(String self) {
        this.self = self;
    }

    //Getter of {id} attribute
    public Long getId() {
        return this.id;
    }

    //Setter of {id} attribute
    public void setId(Long id) {
        this.id = id;
    }

    //Getter of {name} attribute
    public String getName() {
        return this.name;
    }

    //Setter of {name} attribute
    public void setName(String name) {
        this.name = name;
    }

    //Getter of {author} attribute
    public UserOutput getAuthor() {
        return this.author;
    }

    //Setter of {author} attribute
    public void setAuthor(UserOutput author) {
        this.author = author;
    }

    //Getter of {publication} attribute
    public String getPublications() {
        return this.publications;
    }

    //Setter of {publication} attribute
    public void setPublications(String publications) {
        this.publications = publications;
    }

    //Getter of {users} attribute
    public String getUsers() {
        return this.users;
    }

    //Setter of {users} attribute
    public void setUsers(String users) {
        this.users = users;
    }
}