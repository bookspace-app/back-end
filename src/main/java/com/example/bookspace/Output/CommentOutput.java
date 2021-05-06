package com.example.bookspace.Output;

<<<<<<< HEAD
import java.time.LocalDate;

import com.example.bookspace.models.Comment;

public class CommentOutput extends OutputManager{
    private String self = getURL() + "/comments/";
    private Long id;
    private String content;
    private LocalDate dop;
    private Long likes;
    private Integer nreplies;
    private String publication;
    private UserOutput author;
    private String replies;
=======
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
>>>>>>> development


    public CommentOutput(Comment c) {
        this.id = c.getId();
<<<<<<< HEAD
        this.self = self + this.id;;
        this.content = c.getContent();
        this.dop = c.getDop();
        this.likes = c.getLikes();
        this.nreplies = c.getAnswers().size();
        this.publication = new PublicationOutput(c.getPublication()).getSelf();
        this.author = new UserOutput(c.getAuthor());
        this.replies = self + "/replies";
    }


    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
=======
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
>>>>>>> development
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

<<<<<<< HEAD
    public LocalDate getDop() {
        return this.dop;
    }

    public void setDop(LocalDate dop) {
        this.dop = dop;
    }

    public Long getLikes() {
        return this.likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }


    public Integer getNreplies() {
        return this.nreplies;
    }

    public void setNreplies(Integer nreplies) {
        this.nreplies = nreplies;
    }

    public String getPublication() {
        return this.publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
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

    public Integer getTotalLikes() {
        return this.totalLikes;
    }

    public void setTotalLikes(Integer totalLikes) {
        this.totalLikes = totalLikes;
    }

>>>>>>> development
    public UserOutput getAuthor() {
        return this.author;
    }

    public void setAuthor(UserOutput author) {
        this.author = author;
    }

<<<<<<< HEAD
    public String getReplies() {
        return this.replies;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }


    
=======
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


    

>>>>>>> development
}
