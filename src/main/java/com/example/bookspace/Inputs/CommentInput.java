package com.example.bookspace.Inputs;

public class CommentInput {
    private String content;
    private Long authorId;
    private Long publicationId;
    private Long parentId;


    public CommentInput(String content, Long authorId, Long publicationId, Long parentId) {
        this.content = content;
        this.authorId = authorId;
        this.publicationId = publicationId;
        this.parentId = parentId;
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

    public Long getPublicationId() {
        return this.publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    

}
