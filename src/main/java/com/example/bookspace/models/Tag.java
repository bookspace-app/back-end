package com.example.bookspace.models;

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

<<<<<<< HEAD

=======
>>>>>>> development
@Entity
@Table(name = "tag")
//Tag model class
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

    //Default constructor
    public Tag() {}

<<<<<<< HEAD
    public String getTag() {
        return this.tag;
=======
    //Constructor given attributes --> {name, authorId, publication}
    public Tag (String name, User author, Publication publication) {
        this.name = name;
        this.author = author;
        this.publications.add(publication);
    }

    //Constructor given attributes --> {name, authorId}
    public Tag (String name, User author) {
        this.name = name;
        this.author = author;
>>>>>>> development
    }
    
    //Getter of {id} attribute
    public Long getId() {
        return this.id;
    }

    //Setter of {id} attribute
    public void setId(Long id) {
        this.id = id;
    }

    //Getter of {name} attribute
    public String getName() {
        return this.name;
    }

    //Setter of {name} attribute
    public void setName(String name) {
        this.name = name;
    }

    //Getter of {author} attribute
    public User getAuthor() {
        return this.author;
    }

    //Setter of {author} attribute
    public void setAuthor(User author) {
        this.author = author;
    }

<<<<<<< HEAD
=======
    //Getter of {publications} attribute
    public List<Publication> getPublications() {
        return this.publications;
    }
>>>>>>> development

    //Setter of {publications} attribute
    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

<<<<<<< HEAD
    //Test constructor
    public Tag() {
        this.tag = "Tag1";
        this.author = null;
        this.publications = null;
        this.preferedBy = null;
=======
    //Getter of {users} attribute
    public List<User> getUsers() {
        return this.users;
>>>>>>> development
    }

    //Setter of {users} attribute
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
