package com.example.bookspace.Inputs;

import java.util.List;

public class PublicationInput {
    private String title;
    private String content; 
    private Long author_id; 
    private String category;
    private List<Long> tags;
    private List<Long> mentions;

    public PublicationInput(String title, String content, Long author_id, String category, List<Long> mentions, List<Long> tags) {
        this.title = title;
        this.content = content;
        this.author_id = author_id;
        this.category = category;
        this.tags = tags;
        this.mentions = mentions;

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

    public List<Long> getMentions() {
        return this.mentions;
    }

    public void setMentions(List<Long> mentions) {
        this.mentions = mentions;
    }

    public List<Long> getTags() {
        return this.tags;
    }

    public void setTags(List<Long> tags) {
        this.tags = tags;
    }

    

    
}
