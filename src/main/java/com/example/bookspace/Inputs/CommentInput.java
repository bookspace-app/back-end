package com.example.bookspace.Inputs;

public class CommentInput {
    private String content;
    private Long author_id;
    private Long publication_id;
    private Long parent_id;


    public CommentInput(String content, Long author_id, Long publication_id, Long parent_id) {
        this.content = content;
        this.author_id = author_id;
        this.publication_id = publication_id;
        this.parent_id = parent_id;
    }


    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthor_id() {
        return this.author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public Long getPublication_id() {
        return this.publication_id;
    }

    public void setPublication_id(Long publication_id) {
        this.publication_id = publication_id;
    }

    public Long getParent_id() {
        return this.parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

}
