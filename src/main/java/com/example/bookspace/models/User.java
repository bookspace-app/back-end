package com.example.bookspace.models;

import java.beans.Transient;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.example.bookspace.enums.AuthenticationProvider;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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

import com.example.bookspace.enums.Category;
import com.example.bookspace.enums.Rank;




@Entity 
@Table(name = "users")

public class User {

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
    private String email ; 

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password; 

    //date of birth
    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "description")
    private String description = "";

    //@Lob
    @Column(name = "profilePic")
    private String profilePic = "";

    @Column(name = "rank", nullable = false)
    private Rank rank = Rank.WORKER; 

    private String token = "AUTH";

    //date of register
    @Column(name = "dor", nullable = false)
    private LocalDate dor = LocalDate.now(); 

    @ElementCollection
    private List<Category> favCategories = new ArrayList<>();

    /*
    User publication    
    Cascade deletion 
    */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
    private List<Publication> publications = new ArrayList<>();
 
    /*
    User voted publications
    New table is created
    */
    @ManyToMany
    @JoinTable (
        name = "likedPublications", 
        joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "publicationId")

    )
    private List<Publication> likedPublications = new ArrayList<>();

    @ManyToMany
    @JoinTable (
        name = "dislikedPublications", 
        joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "publicationId")

    )
    private List<Publication> dislikedPublications = new ArrayList<>();

    /*
    User favourite publications
    New table is created */
    
    @ManyToMany
    @JoinTable (
        name = "favouritePublications", 
        joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "publicationId")

    )
    private List<Publication> favouritePublications = new ArrayList<>();

    /*
    User reproted publications
    New table is created
    */
    @ManyToMany
    @JoinTable (
        name = "reportedPublications", 
        joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "publicationId")
    )
    private List<Publication> reportedPublications = new ArrayList<>();

    @ManyToMany(mappedBy = "mentions")
    private List<Publication> mentions = new ArrayList<>();

    /*
    User comments
    Cascade deletion
    */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    /*
    Voted comments
    New table on the DB
    */
    @ManyToMany
    @JoinTable (
        name = "likedComments", 
        joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "commentId")

    )
    private List<Comment> likedComments = new ArrayList<>();

    @ManyToMany
    @JoinTable (
        name = "dislikedComments", 
        joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "commentId")

    )
    private List<Comment> dislikedComments = new ArrayList<>();

    @ManyToMany(mappedBy = "commentMentions")
    private List<Comment> commentMentions = new ArrayList<>();

    @Column(name = "createdTags")
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tag> createdTags = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider")
    private AuthenticationProvider authProvider;

    @ManyToMany
    @JoinTable (
        name = "favTags", 
        joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "nameTag")
    )
    private List<Tag> favTags = new ArrayList<>();

    /*
    Blocked users
    */
    @ManyToMany
    @JoinTable (
        name = "blockedUsers",
        joinColumns = @JoinColumn(name="blocker", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "blocked")
    )
    private List<User> blockedUsers = new ArrayList<>();


  

    
    // private Collection<Message> messages;
    // private Collection<Chat> chats;

    public User() {}    
  

    public User(String email, String name, String username, String password, LocalDate dob) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;
        this.dob = dob;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfilePic() {
        return this.profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
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

    public List<Category> getFavCategories() {
        return this.favCategories;
    }

    public void setFavCategories(List<Category> favCategories) {
        this.favCategories = favCategories;
    }

    public void addFavCategory(Category category) {
        this.favCategories.add(category);
    }

    public void removeFavCategory(Category category) {
        this.favCategories.remove(category);
    }
    
    public List<Publication> getPublications() {
        return this.publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public void addPublication(Publication publication) {
        this.publications.add(publication);
        if (this.publications.size() >= 5) {
            this.rank = Rank.SOLDIER;
        }
    }

    public void removePublication(Publication publication) {
        this.publications.remove(publication);
        if (this.publications.size() < 5) {
            this.rank = Rank.WORKER;
        }
    }

    public List<Publication> getLikedPublications() {
        return this.likedPublications;
    }

    public void setLikedPublications(List<Publication> likedPublications) {
        this.likedPublications = likedPublications;
    }

    public void addLikedPublication(Publication publication) {
        this.likedPublications.add(publication);
    }

    public void removeLikedPublication(Publication publication) {
        this.likedPublications.remove(publication);
    }

    public List<Publication> getDislikedPublications() {
        return this.dislikedPublications;
    }

    public void setDislikedPublications(List<Publication> dislikedPublications) {
        this.dislikedPublications = dislikedPublications;
    }

    public void addDislikedPublication(Publication publication) {
        this.dislikedPublications.add(publication);
    }

    public void removeDislikedPublication(Publication publication) {
        this.dislikedPublications.remove(publication);
    }



    public List<Publication> getFavouritePublications() {
        return this.favouritePublications;
    }

    public void setFavouritePublications(List<Publication> favouritePublications) {
        this.favouritePublications = favouritePublications;
    }

    public void addFavPublication(Publication p) {
        this.favouritePublications.add(p);
        if (this.favouritePublications.size() >= 5 && this.rank.equals(Rank.SOLDIER)) {
            this.rank = Rank.HAREM;
        }

        if (this.favouritePublications.size() >= 20 && this.rank.equals(Rank.HAREM)) {
            this.rank = Rank.QUEEN;
        }
    }

    public void removeFavPublication(Publication p) {
        this.favouritePublications.remove(p);
        if (this.favouritePublications.size() < 5 && this.rank.equals(Rank.HAREM)) {
            this.rank = Rank.SOLDIER;
        }

        if (this.favouritePublications.size() < 20  && this.rank.equals(Rank.QUEEN)) {
            this.rank = Rank.HAREM;
        }
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getLikedComments() {
        return this.likedComments;
    }

    public void setLikedComments(List<Comment> likedComments) {
        this.likedComments = likedComments;
    }

    public List<Comment> getDislikedComments() {
        return this.dislikedComments;
    }

    public void setDislikedComments(List<Comment> dislikedComments) {
        this.dislikedComments = dislikedComments;
    }

    public List<Comment> getCommentMentions() {
        return this.commentMentions;
    }

    public void setCommentMentions(List<Comment> commentMentions) {
        this.commentMentions = commentMentions;
    }

    public List<User> getBlockedUsers() {
        return this.blockedUsers;
    }

    public void setBlockedUsers(List<User> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    public List<Tag> getCreatedTags() {
        return this.createdTags;
    }

    public void setCreatedTags(List<Tag> createdTags) {
        this.createdTags = createdTags;
    }

    public void addCreatedTag(Tag tag) {
        this.createdTags.add(tag);
    }

    public List<Tag> getFavTags() {
        return this.favTags;
    }

    public void setfavTags(List<Tag> favTags) {
        this.favTags = favTags;
    }

    public void addFavTag(Tag tag) {
        this.favTags.add(tag);
    }

    public void removeFavTag(Tag tag) {
        this.favTags.remove(tag);
    }
    public List<Publication> getMentions() {
        return this.mentions;
    }

    public void setMentions(List<Publication> mentions) {
        this.mentions = mentions;
    }

    public void addMention(Publication p) {
        this.mentions.add(p);
    }

    public void removeMention(Publication p) {
        this.mentions.remove(p);
    }


    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Transient
    public String getProfilePicPath(){
        return "/user-images/" + this.id + "/" + this.profilePic;
    }

    public List<Publication> getReportedPublications() {
        return this.reportedPublications;
    }
    
    public void setReportedPublications(List<Publication> reportedPublications) {
        this.reportedPublications = reportedPublications;
    }
    
    public void addReportedPublication(Publication publication) {
        this.reportedPublications.add(publication);
    }
    
    public void removeReportedPublication(Publication publication) {
        this.reportedPublications.remove(publication);
    }


    public boolean canCreateTags() {
        if (this.rank == Rank.WORKER && this.createdTags.size() < 2) return true;
        if (this.rank == Rank.SOLDIER && this.createdTags.size() < 7) return true;
        if (this.rank == Rank.HAREM && this.createdTags.size() < 15) return true;
        if (this.rank == Rank.QUEEN) return true;
        else return false; 
    
    }
}
