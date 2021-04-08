package com.example.bookspace.Output;


import com.example.bookspace.models.Tag;
public class TagOutput extends OutputManager{

    private String self = getURL() + "/tags/";
    private Long id;
    private String tag;
    private UserOutput author;
    private String taggedPublications;
    private String preferedByUsers;


    public TagOutput(Tag tag) {
        this.id = tag.getId();
        this.tag = tag.getTag();
        this.author = new UserOutput(tag.getAuthor());
        this.self = self + this.id;
        this.taggedPublications = this.self + "/taggedPublications";
        this.preferedByUsers = this.self + "/preferedByUsers";
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

    public UserOutput getAuthor() {
        return this.author;
    }

    public void setAuthor(UserOutput author) {
        this.author = author;
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