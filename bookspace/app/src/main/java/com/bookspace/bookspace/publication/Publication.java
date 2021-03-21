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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bookspace.bookspace.comment.Comment;
import com.bookspace.bookspace.tags.Tag;
import com.bookspace.bookspace.category.*;
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

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "dop", nullable = false)
    private LocalDate dop; 

    @ManyToOne
    @JoinColumn(name = "publication_owner")
    private User owner;

    @ManyToMany(mappedBy = "voted_publications")
    private Set<User> votedBy;

    @OneToMany(mappedBy = "parent_publication", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "category_publication")
    private Category category;


    @ManyToMany
    @JoinTable (
        name = "tagged_publications", 
        joinColumns = @JoinColumn(name = "publication_id"), 
        inverseJoinColumns = @JoinColumn(name = "tag_name")

    )
    private Set<Tag> tags;
    // private Collection<Chat> chats;



    public Publication() {
    }

    public Publication(String title, String content) {
        this.title = title;
        this.content = content;
        this.dop = LocalDate.now();
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

    public LocalDate getDop() {
        return this.dop;
    }

    public void setDop(LocalDate dop) {
        this.dop = dop;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<User> getVotedBy() {
        return this.votedBy;
    }

    public void setVotedBy(Set<User> votedBy) {
        this.votedBy = votedBy;
    }

    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }


 


    
    
}
