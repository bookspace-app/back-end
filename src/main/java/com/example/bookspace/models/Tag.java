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

import com.example.bookspace.Inputs.TagInput;

@Entity
@Table(name = "tag")
public class Tag {


    public Tag() {}

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
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany(mappedBy = "tags")
    private List<Publication> publications;

    @ManyToMany(mappedBy = "favTags")
    private List<User> preferedBy;

    public Tag(String tag) {
        this.tag = tag;
    }

    public Tag (TagInput tagDetails, User author, Publication publication) {
        this.tag = tagDetails.getTag();
        this.author = author;
        this.publications = new ArrayList<>();
        this.publications.add(publication);

    }

    public Tag(TagInput tagDetails, User author) {
        this.tag = tagDetails.getTag();
        this.author = author;
        this.publications = new ArrayList<>();
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    

    public List<Publication> getTagged_publications() {
        return this.publications;
    }

    public void setTagged_publications(List<Publication> tagged_publications) {
        this.publications = tagged_publications;
    }

    public List<User> getfavTags() {
        return this.preferedBy;
    }

    public void setPrefered_tags(List<User> preferedBy) {
        this.preferedBy = preferedBy;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    



    //Test constructor
    //public Tag() {
    //     this.tag = "Tag1";
    //     this.author = null;
    //     this.publications = null;
    //     this.preferedBy = null;
    // }

}
