package com.example.bookspace.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Column(name = "title", nullable = false, length = 500)
    private String title;

    @Column(name = "content", nullable = false, length = 5000)
    private String content;

    @Column(name = "dop", nullable = false)
    private LocalDateTime dop = LocalDateTime.now(); 

    @Column(name = "views")
    private Integer views = 0;

    @Column(name = "category", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private User author;

    @ManyToMany(mappedBy = "likedPublications")
    private List<User> likedBy = new ArrayList<>();

    @ManyToMany(mappedBy = "dislikedPublications")
    private List<User> dislikedBy = new ArrayList<>();

    @ManyToMany(mappedBy = "favouritePublications")
    private List<User> favouriteBy = new ArrayList<>();

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "directComments")
    private Integer directComments = 0;

    @ManyToMany
    @JoinTable (
        name = "mentionedUsers", 
        joinColumns = @JoinColumn(name = "publicationId"),
        inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private List<User> mentions = new ArrayList<>();

    @ManyToMany
    @JoinTable (
        name = "taggedPublications", 
        joinColumns = @JoinColumn(name = "publicationId"), 
        inverseJoinColumns = @JoinColumn(name = "tagName")

    )
    private List<Tag> tags = new ArrayList<>();


    // private Collection<Chat> chats;



    public Publication() {     
    }

    

    public Publication(String title, String content, User author, Category category) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
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

    public LocalDateTime getDop() {
        return this.dop;
    }

    public void setDop(LocalDateTime dop) {
        this.dop = dop;
    }

    public Integer getViews() {
        return this.views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getTotalLikes() {
        return this.likedBy.size()-this.dislikedBy.size();
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<User> getLikedBy() {
        return this.likedBy;
    }

    public void setLikedBy(List<User> likedBy) {
        this.likedBy = likedBy;
    }
    
    public List<User> getDislikedBy() {
        return this.dislikedBy;
    }

    public void setDislikedBy(List<User> dislikedBy) {
        this.dislikedBy = dislikedBy;
    }

    public void addLikedUser(User user) {
        this.likedBy.add(user);
    }

    public void addDislikedUser(User user) {
        this.dislikedBy.add(user);
    }


    public List<User> getFavouriteBy() {
        return this.favouriteBy;
    }

    public void setFavouriteBy(List<User> favouriteBy) {
        this.favouriteBy = favouriteBy;
    } 

    public void addFavUser(User favUser) {
        this.favouriteBy.add(favUser);
    }

    public void removeFavUser(User user) {
        this.favouriteBy.remove(user);
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    public void addView() {
        this.views++;
    }



    public Integer getLikes() {
        return likedBy.size();
    }



    public Integer getDislikes() {
        return dislikedBy.size();
    }


    public List<User> getMentions() {
        return this.mentions;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    public void addMention(User u) {
        this.mentions.add(u);
    }

    public void removeMention(User u) {
        this.mentions.remove(u);
    }
    public Integer getDirectComments() {
        return this.directComments;
    }

    public void setDirectComments(Integer directComments) {
        this.directComments = directComments;
    }

    public void addDirectComment() {
        this.directComments++;
    }

    public void removeDirectComment() {
        this.directComments--;
    }




    
 
    
}
