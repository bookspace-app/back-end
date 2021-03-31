package com.example.bookspace.Output;

import java.time.LocalDate;

import com.example.bookspace.models.User;

public class UserOutput{

    private String self = "http://localhost:8080/api/users/";
    private Long id;
    private String email;
    private String name;
    private String username;
    private Integer age;
    private String description;
    private String rank; 
    private String publications;
    private String votedPublications;
    private String favouritePublications;
    private String comments; 
    private String votedComments; 
    private String blockedUsers; 
    private String profilePic;
    private String createdTags;
    private String preferedTags; 
    


    public UserOutput(User u) {
        this.id = u.getId();
        this.email = u.getEmail();
        this.name = u.getName();
        this.username = u.getUsername();
        this.age = u.getAge();
        this.description = u.getDescription();
        this.rank = u.getRank().name();
        this.self = self + this.id;
        this.publications =  this.self + "/publications";
        this.votedPublications = this.self + "/votedPublications";
        this.favouritePublications = this.self + "/favouritePublications";
        this.comments = this.self + "/comments";
        this.votedComments = this.self + "/votedComments";
        this.blockedUsers = this.self + "/blockedUsers";
        this.profilePic = this.self + "/profilePic";
        this.createdTags = this.self + "/tags";
        this.preferedTags = this.self + "preferedTags";
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

    public Integer getAge() {
        return this.age;
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

    public String getRank() {
        return this.rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPublications() {
        return this.publications;
    }

    public void setPublications(String publications) {
        this.publications = publications;
    }

    public String getVotedPublications() {
        return this.votedPublications;
    }

    public void setVotedPublications(String votedPublications) {
        this.votedPublications = votedPublications;
    }

    public String getFavouritePublications() {
        return this.favouritePublications;
    }

    public void setFavouritePublications(String favouritePublications) {
        this.favouritePublications = favouritePublications;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getVotedComments() {
        return this.votedComments;
    }

    public void setVotedComments(String votedComments) {
        this.votedComments = votedComments;
    }

    public String getBlockedUsers() {
        return this.blockedUsers;
    }

    public void setBlockedUsers(String blockedUsers) {
        this.blockedUsers = blockedUsers;
    }


    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getProfilePic() {
        return this.profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getCreatedTags() {
        return this.createdTags;
    }

    public void setCreatedTags(String createdTags) {
        this.createdTags = createdTags;
    }

    public String getPreferedTags() {
        return this.preferedTags;
    }

    public void setPreferedTags(String preferedTags) {
        this.preferedTags = preferedTags;
    }
    


    
}
