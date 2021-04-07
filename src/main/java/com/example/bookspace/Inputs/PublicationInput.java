package com.example.bookspace.Inputs;

public class PublicationInput {
    private String title;
    private String content; 
    private Long author_id; 
    private String category;

    public PublicationInput(String title, String content, Long author_id, String category) {
        this.title = title;
        this.content = content;
        this.author_id = author_id;
        this.category = category;
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

    public Long getAuthorId() {
        return this.author_id;
    }

    public void setAuthorId(Long author_id) {
        this.author_id = author_id;
    }


    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    
}
