package com.example.bookspace.models;

import java.time.LocalDate;
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

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "dop")
    private LocalDate dop;

    @Column(name = "likes")
    private Long likes;


    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToMany(mappedBy = "votedComments")
    private List<User> votedBy;

    @ManyToOne
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> answers;


    public Comment() {
        this.id = 2L;
    }

    public Comment(String content, User author, Publication publication) {
        this.content = content;
        this.author = author;
        this.publication = publication;
        this.dop = LocalDate.now();
        this.likes = 0L;
        this.votedBy = new ArrayList<>();
        this.answers = new ArrayList<>();
    }

    public Comment(String content, User author, Publication publication, Comment parent) {
        this.content = content;
        this.author = author;
        this.publication = publication;
        this.parent = parent;
        this.dop = LocalDate.now();
        this.likes = 0L;
        this.votedBy = new ArrayList<>();
        this.answers = new ArrayList<>();
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

    public LocalDate getDop() {
        return this.dop;
    }

    public void setDop(LocalDate dop) {
        this.dop = dop;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<User> getVotedBy() {
        return this.votedBy;
    }

    public void setVotedBy(List<User> votedBy) {
        this.votedBy = votedBy;
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


    public Long getLikes() {
        return this.likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public void addReply(Comment comment) {
        this.answers.add(comment);
    }



    
}
