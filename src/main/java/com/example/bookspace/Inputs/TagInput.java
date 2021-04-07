package com.example.bookspace.Inputs;

public class TagInput { //preguntar si solo se necesita inputear el nombre 
    
    private String tag;
    private Long author_id;
    private Long publication;

    public TagInput() {}

    public TagInput(String tag, Long author_id, Long publication) {
        this.tag = tag;
        this.author_id = author_id;
        this.publication = publication;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getAuthor() {
        return this.author_id;
    }

    public void setAuthor(Long author_id) {
        this.author_id = author_id;
    }

    public Long getPublication() {
        return this.publication;
    }

    public void setPublication(Long Publication) {
        this.publication = Publication;
    }


}
