package com.example.bookspace.Inputs;

import java.util.List;

public class PublicationInput {
    private String title;
    private String content; 
    private Long authorId; 
    private String category;
    private List<Long> tags;
    private List<String> mentions;

    public PublicationInput(String title, String content, Long authorId, String category, List<String> mentions, List<Long> tags) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
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
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Long> getTags() {
        return this.tags;
    }

    public void setTags(List<Long> tags) {
        this.tags = tags;
    }

    public List<String> getMentions() {
        return this.mentions;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }
    
    

    
}
