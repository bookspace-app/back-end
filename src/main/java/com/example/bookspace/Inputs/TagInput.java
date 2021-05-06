package com.example.bookspace.Inputs;

//Class for the Input data of the Tags
public class TagInput { //preguntar si solo se necesita inputear el nombre 
    
    private String name;
    private Long authorId;
    private Long publication;

    //Default constructor
    public TagInput() {}

    //Constructor given attributes --> {name, authorId, publication}
    public TagInput(String tag, Long authorId, Long publication) {
        this.name = tag;
        this.authorId = authorId;
        this.publication = publication;
    }

    //Getter of {name} attribute
    public String getName() {
        return this.name;
    }

    //Setter of {name} attribute
    public void setName(String name) {
        this.name = name;
    }

    //Getter of {authorId} attribute
    public Long getAuthorId() {
        return this.authorId;
    }

    //Setter of {authorId} attribute
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    //Getter of {publication} attribute
    public Long getPublication() {
        return this.publication;
    }

    //Setter of {publication} attribute
    public void setPublication(Long publication) {
        this.publication = publication;
    }
}
