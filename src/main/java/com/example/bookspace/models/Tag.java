package com.example.bookspace.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tag")
//Tag model class
public class Tag {

    @Id 
    private String name;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private User author;

    @ManyToMany(mappedBy = "tags")
    private List<Publication> publications = new ArrayList<>();

    @ManyToMany(mappedBy = "favTags")
    private List<User> favByUsers;

 
    //Default constructor
    public Tag() {}

    
    //Constructor given attributes --> {name, authorId}
    public Tag (String name, User author) {
        this.name = name;
        this.author = author;
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

    //Getter of {publications} attribute
    public List<Publication> getPublications() {
        return this.publications;
    }

    //Setter of {publications} attribute
    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public List<User> getFavByUsers() {
        return this.favByUsers;
    }

    public void setFavByUsers(List<User> favByUsers) {
        this.favByUsers = favByUsers;
    }

    public void removePublication(Publication publication) {
        this.publications.remove(publication);
    }
}
