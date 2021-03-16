package com.bookspace.bookspace.publication;

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

import com.bookspace.bookspace.comment.Comment;
import com.bookspace.bookspace.user.User;

@Entity
@Table(name = "publications")
public class Publication {

    @Id
    @SequenceGenerator(
        name = "publication_sequence", 
        sequenceName = "publication_sequence", 
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "publication_sequence"
    )
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "dop")
    private LocalDate dop; 

    @ManyToOne
    @JoinColumn(name = "publication_owner")
    private User owner;

    @ManyToMany(mappedBy = "voted_publications")
    private Set<User> votedBy;

    @OneToMany(mappedBy = "parent_publication", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;
    // private Theme theme;
    // private Collection<Chat> chats;



    public Publication() {
    }

    public Publication(String title, String content, LocalDate date) {
        this.title = title;
        this.content = content;
        this.dop = date;
    }

    // public Publication(String title, String content, LocalDate date, User owner, Collection<User> votedBy, Collection<Comment> comments, Theme theme, Collection<Chat> chats) {
    //     this.title = title;
    //     this.content = content;
    //     this.date = date;
    //     this.owner = owner;
    //     this.votedBy = votedBy;
    //     this.comments = comments;
    //     this.theme = theme;
    //     this.chats = chats;
    // }


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

    public LocalDate getDate() {
        return this.dop;
    }

    public void setDate(LocalDate date) {
        this.dop = date;
    }



    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    // public Collection<User> getVotedBy() {
    //     return this.votedBy;
    // }

    // public void setVotedBy(Collection<User> votedBy) {
    //     this.votedBy = votedBy;
    // }

    // public Collection<Comment> getComments() {
    //     return this.comments;
    // }

    // public void setComments(Collection<Comment> comments) {
    //     this.comments = comments;
    // }

    // public Theme getTheme() {
    //     return this.theme;
    // }

    // public void setTheme(Theme theme) {
    //     this.theme = theme;
    // }

    // public Collection<Chat> getChats() {
    //     return this.chats;
    // }

    // public void setChats(Collection<Chat> chats) {
    //     this.chats = chats;
    // }
    
}
