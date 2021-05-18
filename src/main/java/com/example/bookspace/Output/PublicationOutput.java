package com.example.bookspace.Output;

import java.time.LocalDateTime;

import com.example.bookspace.models.Publication;


public class PublicationOutput extends OutputManager{ 
    private String selfUri;
    private Long id;
    private String title;
    private String content;
    private LocalDateTime dop;
    private Integer likes;
    private Integer dislikes;
    private Integer nViews;
    private Integer totalLikes;
    private Integer nDirectComments;
    private Integer nComments;
    private String category; 
    private UserOutput author; 
    private String likedByUri;
    private String dislikedByUri;
    private String favByUri;
    private String commentsUri;
    private String tagsUri;   
    private String mentionsUri;

    public PublicationOutput() {

    }

    public PublicationOutput(Publication p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.content = p.getContent();
        this.dop = p.getDop();
        this.likes = p.getLikes();
        this.dislikes = p.getDislikes();
        this.nViews = p.getViews();
        this.totalLikes = p.getTotalLikes();
        this.category = p.getCategory().name();
        this.nComments = p.getComments().size();
        this.author = new UserOutput(p.getAuthor());
        this.category = p.getCategory().name();
        this.nDirectComments = p.getDirectComments();
        this.selfUri = getURL() + "/publications/" + id;
        this.likedByUri = this.selfUri + "/likedBy";
        this.dislikedByUri = this.selfUri + "/dislikedBy";
        this.favByUri = this.selfUri + "/favBy";
        this.commentsUri = this.selfUri + "/comments";
        this.tagsUri = this.selfUri + "/tags";
        this.mentionsUri = this.selfUri + "/mentions";

    }
   



    public String getSelf() {
        return this.selfUri;
    }

    public void setSelf(String self) {
        this.selfUri = self;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDop() {
        return this.dop;
    }

    public void setDop(LocalDateTime dop) {
        this.dop = dop;
    }

    public Integer getLikes() {
        return this.likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return this.dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public Integer getNViews() {
        return this.nViews;
    }

    public void setNViews(Integer nViews) {
        this.nViews = nViews;
    }

    public Integer getTotalLikes() {
        return this.totalLikes;
    }

    public void setTotalLikes(Integer totalLikes) {
        this.totalLikes = totalLikes;
    }

    public Integer getNComments() {
        return this.nComments;
    }

    public void setNComments(Integer nComments) {
        this.nComments = nComments;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public UserOutput getAuthor() {
        return this.author;
    }

    public void setAuthor(UserOutput author) {
        this.author = author;
    }

    public String getLikedByUri() {
        return this.likedByUri;
    }

    public void setLikedByUri(String likedByUri) {
        this.likedByUri = likedByUri;
    }

    public String getDislikedByUri() {
        return this.dislikedByUri;
    }

    public void setDislikedByUri(String dislikedByUri) {
        this.dislikedByUri = dislikedByUri;
    }

    public String getFavByUri() {
        return this.favByUri;
    }

    public void setFavByUri(String favByUri) {
        this.favByUri = favByUri;
    }

    public String getCommentsUri() {
        return this.commentsUri;
    }

    public void setCommentsUri(String commentsUri) {
        this.commentsUri = commentsUri;
    }

    public String getTagsUri() {
        return this.tagsUri;
    }

    public void setTagsUri(String tagsUri) {
        this.tagsUri = tagsUri;
    }

    public String getMentionsUri() {
        return this.mentionsUri;
    }

    public void setMentionsUri(String mentionsUri) {
        this.mentionsUri = mentionsUri;
    }
    
    public String getSelfUri() {
        return this.selfUri;
    }

    public void setSelfUri(String selfUri) {
        this.selfUri = selfUri;
    }

    public Integer getNDirectComments() {
        return this.nDirectComments;
    }

    public void setNDirectComments(Integer nDirectComments) {
        this.nDirectComments = nDirectComments;
    }

    
    
    


    



    


    
}
