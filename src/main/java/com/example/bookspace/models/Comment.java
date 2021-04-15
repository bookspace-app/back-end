package com.example.bookspace.models;

import java.time.LocalDateTime;
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

    @Column(name = "content")
    private String content;

    @Column(name = "dop")
    private LocalDateTime dop;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany(mappedBy = "likedComments")
    private List<User> likedBy;

    @ManyToMany(mappedBy = "dislikedComments")
    private List<User> dislikedBy;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> answers;


    public Comment() {
        this.id = 2L;
    }

    // public Comment(String content, LocalDate date) {
    //     this.content = content;
    //     this.dop = date;
    // }


    // public Comment(String content, LocalDate date, User user, Collection<User> votedBy, Publication publication, Comment parent, Collection<Comment> answers) {
    //     this.content = content;
    //     this.dop = dop;
    //     this.owner = owner;
    //     this.votedBy = votedBy;
    //     this.publication = publication;
    //     this.parent = parent;
    //     this.answers = answers;
    // }

    // public String getContent() {
    //     return this.content;
    // }

    // public void setContent(String content) {
    //     this.content = content;
    // }


    // public LocalDate getDate() {
    //     return this.date;
    // }

    // public void setDate(LocalDate date) {
    //     this.date = date;
    // }

    // public User getUser() {
    //     return this.user;
    // }


    // public Collection<User> getVotedBy() {
    //     return this.votedBy;
    // }


    // public Publication getPublication() {
    //     return this.publication;
    // }


    // public Comment getParent() {
    //     return this.parent;
    // }


    // public Collection<Comment> getAnswers() {
    //     return this.answers;
    // }


    
}
