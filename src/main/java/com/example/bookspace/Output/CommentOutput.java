package com.example.bookspace.Output;

import java.time.LocalDateTime;

import com.example.bookspace.models.Comment;

public class CommentOutput extends OutputManager {
    private String selfUri;
    private Long id;
    private String content;
    private LocalDateTime dop;
    private Integer likes;
    private Integer dislikes;
    private Integer totalLikes;
    private Long parentId;
    private UserOutput author;
    private Integer nReplies;
    private Long publicationId;
    private String mentionsUri;
    private String likedByUri;
    private String dislikedByUri;
    private String repliesUri;
    

    public CommentOutput(){}


    public CommentOutput(Comment c) {
        this.id = c.getId();
        this.content = c.getContent();
        this.dop = c.getDop();
        this.likes = c.getLikedBy().size();
        this.dislikes = c.getDislikedBy().size();
        this.nReplies = c.getReplies().size();
        this.author = new UserOutput(c.getAuthor());
        this.totalLikes = this.likes-this.dislikes;
        this.selfUri = getURL() + "/comments/" + this.id;
        this.publicationId = c.getPublication().getId();
        this.mentionsUri = this.selfUri + "/mentions";
        this.likedByUri = this.selfUri + "/like";
        this.dislikedByUri = this.selfUri + "/dislike";
        this.repliesUri = this.selfUri + "/replies";
        if (c.getParent() != null) this.parentId = c.getParent().getId();
        else this.parentId = 0L;
    }



    public String getSelfUri() {
        return this.selfUri;
    }

    public void setSelfUri(String selfUri) {
        this.selfUri = selfUri;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getTotalLikes() {
        return this.totalLikes;
    }

    public void setTotalLikes(Integer totalLikes) {
        this.totalLikes = totalLikes;
    }

    public UserOutput getAuthor() {
        return this.author;
    }

    public void setAuthor(UserOutput author) {
        this.author = author;
    }

    public Integer getNReplies() {
        return this.nReplies;
    }

    public void setNReplies(Integer nReplies) {
        this.nReplies = nReplies;
    }


    public Long getPublicationId() {
        return this.publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }
    


    public String getMentionsUri() {
        return this.mentionsUri;
    }

    public void setMentionsUri(String mentionsUri) {
        this.mentionsUri = mentionsUri;
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

    public String getRepliesUri() {
        return this.repliesUri;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public void setRepliesUri(String repliesUri) {
        this.repliesUri = repliesUri;
    }


    

}
