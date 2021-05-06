package com.example.bookspace.Inputs;

<<<<<<< HEAD
public class CommentInput {
    String content;
    Long author;
    Long parent;

    public CommentInput(String content, Long author, Long parent) {
        this.content = content;
        this.author = author;
        this.parent = parent;
=======
import java.util.List;

public class CommentInput {
    private String content;
    private Long authorId;
    private Long publicationId;
    private Long parentId;
    private List<Long> mentions;


    public CommentInput(String content, Long authorId, Long publicationId, Long parentId, List<Long> mentions) {
        this.content = content;
        this.authorId = authorId;
        this.publicationId = publicationId;
        this.parentId = parentId;
        this.mentions = mentions;
>>>>>>> development
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
    }


    public Long getParent() {
        return this.parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }



    
=======
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

    public List<Long> getMentions() {
        return this.mentions;
    }

    public void setMentions(List<Long> mentions) {
        this.mentions = mentions;
    }

    

>>>>>>> development
}
