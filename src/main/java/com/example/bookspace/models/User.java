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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.bookspace.enums.Rank;

import org.hibernate.type.ImageType;



@Entity 
@Table(name = "users")

public class User{

    public User(){}

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

    @Column(name = "email", unique = true, nullable = false)
    private String email; 

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    //date of birth
    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "rank", nullable = false)
    private Rank rank; 

    //date of register
    @Column(name = "dor", nullable = false)
    private LocalDate dor; 

    /*
    User publication    
    Cascade deletion 
    */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
    private List<Publication> publications;
 
    /*
    User voted publications
    New table is created
    */
    @ManyToMany
    @JoinTable (
        name = "votedPublications", 
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "publication_id")

    )
    private List<Publication> votedPublications;

    /*
    User favourite publications
    New table is created
    */
    @ManyToMany
    @JoinTable (
        name = "favouritePublications", 
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "publication_id")

    )
    private List<Publication> favouritePublications;


    /*
    User comments
    Cascade deletion
    */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;


    /*
    Voted comments
    New table on the DB
    */
    @ManyToMany
    @JoinTable (
        name = "votedComments", 
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "comment_id")

    )
    private List<Comment> votedComments;

    /*
    Blocked users
    */
    @ManyToMany
    @JoinTable (
        name = "blockedUsers",
        joinColumns = @JoinColumn(name="blocker", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "blocked")
    )
    private List<User> blockedUsers;

    @Transient //This attribute can be calculated from some other attributes
    private Integer age;

    @Column(name = "profile_pic")
    private ImageType profile_pic;

    @Column(name = "createdTags")
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tag> createdTags;


    @ManyToMany
    @JoinTable (
        name = "preferedTags", 
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "name_tag")
    )
    private List<Tag> preferedTags;

    
    // private Collection<Message> messages;
    // private Collection<Chat> chats;


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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Publication> getPublications() {
        return this.publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public List<Publication> getVotedPublications() {
        return this.votedPublications;
    }

    public void setVotedPublications(List<Publication> votedPublications) {
        this.votedPublications = votedPublications;
    }

    public List<Publication> getFavouritePublications() {
        return this.favouritePublications;
    }

    public void setFavouritePublications(List<Publication> favouritePublications) {
        this.favouritePublications = favouritePublications;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getVotedComments() {
        return this.votedComments;
    }

    public void setVotedComments(List<Comment> votedComments) {
        this.votedComments = votedComments;
    }

    public List<User> getBlockedUsers() {
        return this.blockedUsers;
    }

    public void setBlockedUsers(List<User> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ImageType getProfile_pic() {
        return this.profile_pic;
    }

    public void setProfile_pic(ImageType profile_pic) {
        this.profile_pic = profile_pic;
    }

    public List<Tag> getCreatedTags() {
        return this.createdTags;
    }

    public void setCreatedTags(List<Tag> createdTags) {
        this.createdTags = createdTags;
    }

    public List<Tag> getPreferedTags() {
        return this.preferedTags;
    }

    public void setPreferedTags(List<Tag> preferedTags) {
        this.preferedTags = preferedTags;
    }
   

    public void votePublication(Publication p) throws Exception {

        if (!this.publications.contains(p))  this.votedPublications.add(p);        
        else throw new Exception("A User can not vote it's own publication");
    }


    public void addPublication(Publication publication) {
        this.publications.add(publication);
    }


    
    


       

}
