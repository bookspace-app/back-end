package com.example.bookspace.Inputs;

import java.util.List;

import com.example.bookspace.models.Publication;
import com.example.bookspace.models.User;

public class TagInput { //preguntar si solo se necesita inputear el nombre 
    
    private String tag;
    private User author;
    private List<Publication> taggedPublications;


    public TagInput(String tag, User author, List<Publication> taggedPublications) {
        this.tag = tag;
        // this.author = author;
        // this.taggedPublications = taggedPublications;
    }
    

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Publication> getTaggedPublications() {
        return this.taggedPublications;
    }

    public void setTaggedPublications(List<Publication> taggedPublications) {
        this.taggedPublications = taggedPublications;
    }
    

}
