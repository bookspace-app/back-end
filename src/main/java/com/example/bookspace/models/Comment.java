package com.example.bookspace.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table (name = "comments")
public class Comment {
    @Id
    @SequenceGenerator(
        name = "comment_sequence", 
        sequenceName = "comment_sequence", 
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "comment_sequence"
    )
    private Long id;

    @Column(name = "content", nullable = false, length = 5000)
    private String content;

    @Column(name = "dop", nullable = false)
    private LocalDateTime dop = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "authorId", nullable =  false)
    private User author;

    @ManyToMany(mappedBy = "likedComments")
    private List<User> likedBy = new ArrayList<>();

    @ManyToMany(mappedBy = "dislikedComments")
    private List<User> dislikedBy = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "publicationId", nullable = false)
    private Publication publication;

    @ManyToOne
    @JoinColumn(name = "parentId")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> answers = new ArrayList<>();


    public Comment() {

    }

    public Comment(String content, User author, Publication publication) {
        this.content = content;
        this.author = author;
        this.publication = publication;
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

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<User> getLikedBy() {
        return this.likedBy;
    }

    public void setLikedBy(List<User> likedBy) {
        this.likedBy = likedBy;
    }

    public void addLikedBy(User u) {
        this.likedBy.add(u);
    }

    public void removeLikeBy(User u) {
        this.likedBy.remove(u);
    }

    public List<User> getDislikedBy() {
        return this.dislikedBy;
    }

    public void setDislikedBy(List<User> dislikedBy) {
        this.dislikedBy = dislikedBy;
    }

    public void addDislikedBy(User u) {
        this.dislikedBy.add(u);
    }

    public void removeDislikedBy(User u) {
        this.dislikedBy.remove(u);
    }

    public Publication getPublication() {
        return this.publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Comment getParent() {
        return this.parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public List<Comment> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<Comment> answers) {
        this.answers = answers;
    }

    public void addAnswer(Comment answer) {
        this.answers.add(answer);
    }

    public void removeAnswer(Comment answer) {
        this.answers.remove(answer);
    }


  
}
