package com.example.bookspace.Inputs;

public class TagInput { //preguntar si solo se necesita inputear el nombre 
    
    private String name;
    private Long author_id;
    private Long publication;

    public TagInput() {}

    public TagInput(String tag, Long author_id, Long publication) {
        this.name = tag;
        this.author_id = author_id;
        this.publication = publication;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
