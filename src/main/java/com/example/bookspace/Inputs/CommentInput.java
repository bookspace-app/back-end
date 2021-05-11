package com.example.bookspace.Inputs;

import java.util.List;

public class CommentInput {
    private String content;
    private Long authorId;
    private Long publicationId;
    private Long parentId;
    private List<String> mentions;


    public CommentInput(String content, Long authorId, Long publicationId, Long parentId, List<String> mentions) {
        this.content = content;
        this.authorId = authorId;
        this.publicationId = publicationId;
        this.parentId = parentId;
        this.mentions = mentions;
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

    public List<String> getMentions() {
        return this.mentions;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }

    

}
