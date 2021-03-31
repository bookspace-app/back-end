package com.example.bookspace.models;

import java.util.Set;

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
    private String tag;

    @ManyToOne
    @JoinColumn(name = "tags_created")
    private User owner;

    @ManyToMany(mappedBy = "tags")
    private Set<Publication> tagged_publications;

    @ManyToMany(mappedBy = "prefered_tags")
    private Set<User> prefered_tags;

    public Tag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Publication> getTagged_publications() {
        return this.tagged_publications;
    }

    public void setTagged_publications(Set<Publication> tagged_publications) {
        this.tagged_publications = tagged_publications;
    }

    public Set<User> getPrefered_tags() {
        return this.prefered_tags;
    }

    public void setPrefered_tags(Set<User> prefered_tags) {
        this.prefered_tags = prefered_tags;
    }

    //Test constructor
    public Tag() {
        this.tag = "Tag1";
        this.owner = null;
        this.tagged_publications = null;
        this.prefered_tags = null;
    }

}
