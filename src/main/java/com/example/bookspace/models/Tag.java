package com.example.bookspace.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @SequenceGenerator(
        name = "tag_sequence", 
        sequenceName = "tag_sequence", 
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "tag_sequence"
    )
    private Long id;
    
    @Column(name = "tag", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private User author;

    @ManyToMany(mappedBy = "tags")
    private List<Publication> publications = new ArrayList<>();

    @ManyToMany(mappedBy = "favTags")
    private List<User> users = new ArrayList<>();

    
    public Tag() {}

    public Tag (String name, User author, Publication publication) {
        this.name = name;
        this.author = author;
        this.publications.add(publication);
    }

    public Tag (String name, User author, List<Publication> publications) {
        this.name = name;
        this.author = author;
        this.publications = publications;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Publication> getPublications() {
        return this.publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public void addPublication(Publication p) {
        this.publications.add(p);
    }

    public void removePublications(Publication p) {
        this.publications.remove(p);
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

   
}
