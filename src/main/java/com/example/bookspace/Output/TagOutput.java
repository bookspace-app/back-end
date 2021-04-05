package com.example.bookspace.Output;


import com.example.bookspace.models.Tag;

public class TagOutput extends OutputManager{

    private String self = getURL() + "/tags/";
    private Long id;
    private String tag;
    private Long authorId;
    private String taggedPublications;
    private String preferedByUsers;

    public TagOutput(Tag t) {
        this.id = t.getId();
        this.tag = t.getTag();
        //this.authorId = t.getAuthor().getId();
        this.self = self + this.id;
        this.taggedPublications = this.self + "/publications";
        this.preferedByUsers = this.self + "/users";
    }


    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getTaggedPublications() {
        return this.taggedPublications;
    }

    public void setTaggedPublications(String taggedPublications) {
        this.taggedPublications = taggedPublications;
    }

    public String getPreferedByUsers() {
        return this.preferedByUsers;
    }

    public void setPreferedByUsers(String preferedByUsers) {
        this.preferedByUsers = preferedByUsers;
    }

    

}
