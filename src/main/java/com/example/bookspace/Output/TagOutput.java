package com.example.bookspace.Output;

import com.example.bookspace.models.Tag;

//Class for the Output data of the Tags
public class TagOutput extends OutputManager {

    private String self = getURL() + "/tags/";
    private String name;
    private UserOutput author;
    private String publicationsUri;
    private String usersUri;

    //Default constructor
    public TagOutput() {}

    //Constructor given a Tag
    public TagOutput(Tag tag) {
        this.name = tag.getName();
        this.author = new UserOutput(tag.getAuthor());
        this.publicationsUri = this.self + "/publications";
        this.usersUri = this.self + "/users";
    }

    //Getter of {self} attribute
    public String getSelf() {
        return this.self;
    }

    //Setter of {self} attribute
    public void setSelf(String self) {
        this.self = self;
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
        return this.publicationsUri;
    }

    //Setter of {publication} attribute
    public void setPublications(String publications) {
        this.publicationsUri = publications;
    }

    //Getter of {users} attribute
    public String getUsers() {
        return this.usersUri;
    }

    //Setter of {users} attribute
    public void setUsers(String users) {
        this.usersUri = users;
    }
}