package com.example.bookspace.Inputs;

import java.util.List;

public class PublicationInput {
    private String title;
    private String content; 
<<<<<<< HEAD
    private Long author;
=======
    private Long authorId; 
>>>>>>> development
    private String category;
    private List<Long> tags;
    private List<Long> mentions;

<<<<<<< HEAD
    public PublicationInput(String title, String content, Long author, String category) {
        this.title = title;
        this.content = content;
        this.author = author;
=======
    public PublicationInput(String title, String content, Long authorId, String category, List<Long> mentions, List<Long> tags) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
>>>>>>> development
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

<<<<<<< HEAD
    public Long getAuthor() {
        return this.author;
    }

    public void setAuthor(Long author) {
        this.author = author;
=======
    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
>>>>>>> development
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

    public List<Long> getMentions() {
        return this.mentions;
    }

    public void setMentions(List<Long> mentions) {
        this.mentions = mentions;
    }
    
    

    
}
