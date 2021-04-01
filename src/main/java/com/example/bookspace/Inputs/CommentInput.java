package com.example.bookspace.Inputs;

public class CommentInput {
    String content;
    Long author;
    Long parent;

    public CommentInput(String content, Long author, Long parent) {
        this.content = content;
        this.author = author;
        this.parent = parent;
    }


    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthor() {
        return this.author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }


    public Long getParent() {
        return this.parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }



    
}
