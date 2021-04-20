package com.example.bookspace.Inputs;

public class TagInput { //preguntar si solo se necesita inputear el nombre 
    
    private String name;
    private Long authorId;
    private Long publication;

    public TagInput() {}

    public TagInput(String tag, Long authorId, Long publication) {
        this.name = tag;
        this.authorId = authorId;
        this.publication = publication;
    }



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getPublication() {
        return this.publication;
    }

    public void setPublication(Long publication) {
        this.publication = publication;
    }
    


}
