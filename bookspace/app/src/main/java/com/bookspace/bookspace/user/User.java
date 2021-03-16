package com.bookspace.bookspace.user;

import java.time.LocalDate;
import java.time.Period;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bookspace.bookspace.comment.Comment;
import com.bookspace.bookspace.enums.Rank;
import com.bookspace.bookspace.publication.Publication;

import org.hibernate.type.ImageType;



@Entity 
@Table(name = "users")

public class User{

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

    @Column(name = "email", unique = true)
    private String email; 

    @Column(name = "name")
    private String name;

    @Column(name = "username", unique = true)
    private String username;

    //date of birth
    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "description")
    private String description;

    @Column(name = "rank")
    private Rank rank; 

    //date of register
    @Column(name = "dor")
    private LocalDate dor; 

    /*
    User publication    
    Cascade deletion 
    */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
    private Set<Publication> publications;
 
    /*
    User publications
    New table is created
    */
    @ManyToMany
    @JoinTable (
        name = "voted_publications", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "publication_id")

    )
    private Set<Publication> voted_publications;


    /*
    User comments
    Cascade deletion
    */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;


    /*
    Voted comments
    New table on the DB
    */
    @ManyToMany
    @JoinTable (
        name = "voted_comments", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "comment_id")

    )
    private Set<Comment> voted_comments;

    /*
    Blocked users
    */
    @ManyToMany
    @JoinTable (
        name = "blocked_users",
        joinColumns = @JoinColumn(name="blocker"), 
        inverseJoinColumns = @JoinColumn(name = "blocked")
    )
    private Set<User> blocked_users;

    @Transient //This attribute can be calculated from some other attributes
    private Integer age;

    @Column(name = "profile_pic")
    private ImageType profile_pic;

    
    // private Collection<Message> messages;
    // private Collection<Chat> chats;


    public User() {
    }

    


    public User(String email, String name, String username, LocalDate dob, String description) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.dob = dob;
        this.description = description;
        this.setDor(LocalDate.now());
        this.setRank(Rank.WORKER);
        

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Rank getRank() {
        return this.rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public LocalDate getDor() {
        return this.dor;
    }

    public void setDor(LocalDate dor) {
        this.dor = dor;
    }

    public Set<Publication> getPublications() {
        return this.publications;
    }

    public void setPublications(Set<Publication> publications) {
        this.publications = publications;
    }

    public Set<Publication> getVoted_publications() {
        return this.voted_publications;
    }

    public void setVoted_publications(Set<Publication> voted_publications) {
        this.voted_publications = voted_publications;
    }

    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Comment> getVoted_comments() {
        return this.voted_comments;
    }

    public void setVoted_comments(Set<Comment> voted_comments) {
        this.voted_comments = voted_comments;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

       

}
