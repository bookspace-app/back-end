package com.example.bookspace.Output;


import java.time.LocalDate;

import com.example.bookspace.models.User;


public class UserOutput extends OutputManager{

    private String self ;
    private Long id;
    private String email;
    private String name;
    private String username;
    private String password;
    private Integer age;
    private String description;
    private LocalDate dor;
    private String rank; 
    private String publications;
    private String votedPublications;
    private String favouritePublications;
    private String comments; 
    private String votedComments; 
    private String blockedUsers; 
    private String profilePic;
    private String createdTags;
    private String favTags; 
    private String favCategories;
    
    public UserOutput(User u) {
        this.id = u.getId();
        this.email = u.getEmail();
        this.name = u.getName();
        this.username = u.getUsername();
        this.password = u.getPassword();
        this.age = u.getAge();
        this.description = u.getDescription();
        this.dor = u.getDor();
        this.rank = u.getRank().name();
        this.self = getURL() + "/users/" + this.id;
        this.publications =  this.self + "/publications";
        this.votedPublications = this.self + "/votedPublications";
        this.favouritePublications = this.self + "/favouritePublications";
        this.comments = this.self + "/comments";
        this.votedComments = this.self + "/votedComments";
        this.blockedUsers = this.self + "/blockedUsers";
        this.profilePic = this.self + "/profilePic";
        this.createdTags = this.self + "/tags";
        this.favTags = this.self + "/favTags";
        this.favCategories = this.self + "/favCategories";
    }



    public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
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

    public LocalDate getDor() {
        return this.dor;
    }

    public void setDor(LocalDate dor) {
        this.dor = dor;
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

    public String getfavTags() {
        return this.favTags;
    }

    public void setfavTags(String favTags) {
        this.favTags = favTags;
    }


    public String getFavCategories() {
        return this.favCategories;
    }

    public void setFavCategories(String favCategories) {
        this.favCategories = favCategories;
    }


    


    
}
