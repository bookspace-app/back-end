package com.example.bookspace.Output;

import java.time.LocalDateTime;

import com.example.bookspace.models.Comment;
import com.example.bookspace.models.Publication;

public class MentionOutput extends OutputManager{
   
    private Long id;
    private UserOutput author;
    private LocalDateTime dop; 
    private String type;
    private String uri;
    private String content;

    public MentionOutput() {}

    public MentionOutput(Publication p) {
        this.id = p.getId();
        this.author = new UserOutput(p.getAuthor());
        this.dop = p.getDop();
        this.type = "publication";
        this.content = p.getContent();
        this.uri = getURL() + "/publications/" + p.getId();


    }

    public MentionOutput(Comment c) {

        this.id = c.getId();
        this.author = new UserOutput(c.getAuthor());
        this.dop = c.getDop();
        this.type = "comment";
        this.content = c.getContent();
        this.uri = getURL() + "/comments/" + c.getId();

    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public UserOutput getAuthor() {
        return this.author;
    }

    public void setAuthor(UserOutput author) {
        this.author = author;
    }

    public LocalDateTime getDop() {
        return this.dop;
    }

    public void setDop(LocalDateTime dop) {
        this.dop = dop;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
