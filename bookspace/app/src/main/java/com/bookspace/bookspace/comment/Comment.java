package com.bookspace.bookspace.comment;

import java.time.LocalDate;
import java.util.Set;

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

import com.bookspace.bookspace.publication.Publication;
import com.bookspace.bookspace.user.User;

@Entity
@Table (name = "comments")
public class Comment {
    @Id
    @SequenceGenerator(
        name = "user_sequence", 
        sequenceName = "user_sequence", 
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "user_sequence"
    )
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "dop")
    private LocalDate dop;

    @ManyToOne
    @JoinColumn(name = "comment_owner")
    private User owner;

    @ManyToMany(mappedBy = "voted_comments")
    private Set<User> votedBy;

    @ManyToOne
    @JoinColumn(name = "parent_publications")
    private Publication parent_publication;

    @ManyToOne
    @JoinColumn(name = "parent_comment")
    private Comment parent_comment;

    @OneToMany(mappedBy = "parent_comment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> children;


    public Comment() {
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
