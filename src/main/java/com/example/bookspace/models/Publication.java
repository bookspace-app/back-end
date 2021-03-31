package com.example.bookspace.models;

import java.time.LocalDate;
import java.util.List;

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

import com.example.bookspace.enums.Category;


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
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany(mappedBy = "votedPublications")
    private List<User> votedBy;

    @ManyToMany(mappedBy = "favouritePublications")
    private List<User> favouriteBy;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @Column
    private Category category;


    @ManyToMany
    @JoinTable (
        name = "tagged_publications", 
        joinColumns = @JoinColumn(name = "publication_id"), 
        inverseJoinColumns = @JoinColumn(name = "tag_name")

    )
    private List<Tag> tags;
    // private Collection<Chat> chats;



    public Publication() {     
    }

    public Publication(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.dop = LocalDate.now();
        this.author = author;
        this.votedBy = null;
        this.favouriteBy = null;
        this.comments = null;
        this.category = null;
        this.tags = null;
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

    public List<User> getVotedBy() {
        return this.votedBy;
    }

    public void setVotedBy(List<User> votedBy) {
        this.votedBy = votedBy;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
   

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<User> getFavouriteBy() {
        return this.favouriteBy;
    }

    public void setFavouriteBy(List<User> favouriteBy) {
        this.favouriteBy = favouriteBy;
    }
    
}
