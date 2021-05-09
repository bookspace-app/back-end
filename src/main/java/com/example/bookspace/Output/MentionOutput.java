package com.example.bookspace.Output;

import java.time.LocalDateTime;

import com.example.bookspace.models.Comment;
import com.example.bookspace.models.Publication;

public class MentionOutput extends OutputManager{
   

    private UserOutput author;
    private LocalDateTime dop; 
    private String type;
    private String uri;

    public MentionOutput() {}

    public MentionOutput(Publication p) {
        this.author = new UserOutput(p.getAuthor());
        this.dop = p.getDop();
        this.type = "publication";
        this.uri = getURL() + "/publications/" + p.getId();

    }

    public MentionOutput(Comment c) {
        this.author = new UserOutput(c.getAuthor());
        this.dop = c.getDop();
        this.type = "comment";
        this.uri = getURL() + "/comments/" + c.getId();

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

}
