package com.example.bookspace.Inputs;

public class TagInput { //preguntar si solo se necesita inputear el nombre 
    
    private String tag;
    private Long author;
    private Long publication;

    public TagInput() {}

    public TagInput(String tag, Long author, Long publication) {
        this.tag = tag;
        this.author = author;
        this.publication = publication;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
