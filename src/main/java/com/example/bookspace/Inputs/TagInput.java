package com.example.bookspace.Inputs;

public class TagInput { //preguntar si solo se necesita inputear el nombre 
    
    private String name;
    private Long author;
    private Long publication;

    public TagInput() {}

    public TagInput(String tag, Long author, Long publication) {
        this.name = tag;
        this.author = author;
        this.publication = publication;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public Long getAuthor() {
        return this.author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Long getPublication() {
        return this.publication;
    }

    public void setPublication(Long Publication) {
        this.publication = Publication;
    }


}
