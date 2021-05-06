package com.example.bookspace.Output;

<<<<<<< HEAD
import java.time.LocalDate;

import com.example.bookspace.models.Publication;

=======
import java.time.LocalDateTime;

import com.example.bookspace.models.Publication;


>>>>>>> development
public class PublicationOutput extends OutputManager{ 
    private String selfUri;
    private Long id;
    private String title;
    private String content;
<<<<<<< HEAD
    private LocalDate dop;
    private Long views;
    private Long likes;
    private String category; 
    private UserOutput author;
    private String votedusers;
    private String favusers;
    private String comments;
    private String tags;
    private Integer nComments;
    
=======
    private LocalDateTime dop;
    private Integer likes;
    private Integer dislikes;
    private Integer nViews;
    private Integer totalLikes;
    private Integer nComments;
    private String category; 
    private UserOutput author; 
    private String likedByUri;
    private String dislikedByUri;
    private String favByUri;
    private String commentsUri;
    private String tagsUri;   
    private String mentionsUri;
>>>>>>> development

    public PublicationOutput() {

    }

    public PublicationOutput(Publication p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.content = p.getContent();
        this.dop = p.getDop();
<<<<<<< HEAD
        this.views = p.getViews();
        this.author = new UserOutput(p.getAuthor());
        this.votedusers = self + "/votedusers";
        this.favusers = self + "/favUsers";
        this.comments = self + "/comments";
        this.category = p.getCategory().name();
        this.tags = self + "/tags";
        this.likes = p.getLikes();
        this.nComments = p.getComments().size();


    }

    
=======
        this.likes = p.getLikes();
        this.dislikes = p.getDislikes();
        this.nViews = p.getViews();
        this.totalLikes = p.getTotalLikes();
        this.category = p.getCategory().name();
        this.nComments = p.getComments().size();
        this.author = new UserOutput(p.getAuthor());
        this.category = p.getCategory().name();
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
>>>>>>> development

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

<<<<<<< HEAD
    public UserOutput getAuthor() {
        return this.author;
    }

    public void setAuthor(UserOutput author) {
        this.author = author;
    }


    public LocalDate getDop() {
        return this.dop;
    }

    public void setDop(LocalDate dop) {
        this.dop = dop;
    }      
=======
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
>>>>>>> development

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



<<<<<<< HEAD
    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
=======
    public String getLikedByUri() {
        return this.likedByUri;
    }

    public void setLikedByUri(String likedByUri) {
        this.likedByUri = likedByUri;
    }

    public String getDislikedByUri() {
        return this.dislikedByUri;
>>>>>>> development
    }
    

<<<<<<< HEAD
    public String getFavusers() {
        return this.favusers;
    }

    public void setFavusers(String favusers) {
        this.favusers = favusers;
=======
    public void setDislikedByUri(String dislikedByUri) {
        this.dislikedByUri = dislikedByUri;
    }

    public String getFavByUri() {
        return this.favByUri;
>>>>>>> development
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
    

    public String getVotedusers() {
        return this.votedusers;
    }

    public void setVotedusers(String votedusers) {
        this.votedusers = votedusers;
    }

    public void setTagsUri(String tagsUri) {
        this.tagsUri = tagsUri;
    }

<<<<<<< HEAD
    public Long getViews() {
        return this.views;
    }

    public void setViews(Long views) {
        this.views = views;
=======
    public String getMentionsUri() {
        return this.mentionsUri;
    }

    public void setMentionsUri(String mentionsUri) {
        this.mentionsUri = mentionsUri;
>>>>>>> development
    }
    

    
    
    


    


    public Long getLikes() {
        return this.likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }
    

    public Integer getNComments() {
        return this.nComments;
    }

    public void setNComments(Integer nComments) {
        this.nComments = nComments;
    }

    
}
