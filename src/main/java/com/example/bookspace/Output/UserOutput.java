package com.example.bookspace.Output;


import java.time.LocalDate;

import com.example.bookspace.models.User;


public class UserOutput extends OutputManager{

    private String selfUri;
    private Long id;
    private String email;
    private String name;
    private String username;
    private String password;
    private Integer age;
    private String description;
    private LocalDate dor;
    private String rank; 
    private String publicationsUri;
    private String likedPublicationsUri;
    private String dislikedPublicationsUri;
    private String favouritePublicationsUri;
    private String commentsUri;     
    private String likedCommentsUri;
    private String dislikedCommentsUri; 
    private String blockedUsersUri; 
    private String profilePicUri;
    private String createdTagsUri;
    private String favTagsUri; 
    private String favCategoriesUri;
    private String mentionsUri;
    
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
        this.selfUri = getURL() + "/users/" + this.id;
        this.publicationsUri =  this.selfUri + "/publications";
        this.likedPublicationsUri = this.selfUri + "/likedPublications";
        this.dislikedPublicationsUri = this.selfUri + "/dislikedPublications";
        this.favouritePublicationsUri = this.selfUri + "/favPublications";
        this.commentsUri = this.selfUri + "/comments";
        this.likedCommentsUri = this.selfUri + "/likedComments";
        this.dislikedCommentsUri = this.selfUri + "/dislikedComments";
        this.blockedUsersUri = this.selfUri + "/blockedUsers";
        this.profilePicUri = this.selfUri + "/profilePic";
        this.createdTagsUri = this.selfUri + "/tags";
        this.favTagsUri = this.selfUri + "/favTags";
        this.favCategoriesUri = this.selfUri + "/categories";
        this.mentionsUri = this.selfUri + "/mentions";
    }



    public String getSelf() {
        return this.selfUri;
    }

    public void setSelf(String self) {
        this.selfUri = self;
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


    public String getPublicationsUri() {
        return this.publicationsUri;
    }

    public void setPublicationsUri(String publicationsUri) {
        this.publicationsUri = publicationsUri;
    }

    public String getLikedPublicationsUri() {
        return this.likedPublicationsUri;
    }

    public void setLikedPublicationsUri(String likedPublicationsUri) {
        this.likedPublicationsUri = likedPublicationsUri;
    }

    public String getDislikedPublicationsUri() {
        return this.dislikedPublicationsUri;
    }

    public void setDislikedPublicationsUri(String dislikedPublicationsUri) {
        this.dislikedPublicationsUri = dislikedPublicationsUri;
    }

    public String getFavouritePublicationsUri() {
        return this.favouritePublicationsUri;
    }

    public void setFavouritePublicationsUri(String favouritePublicationsUri) {
        this.favouritePublicationsUri = favouritePublicationsUri;
    }

    public String getCommentsUri() {
        return this.commentsUri;
    }

    public void setCommentsUri(String commentsUri) {
        this.commentsUri = commentsUri;
    }

    public String getLikedCommentsUri() {
        return this.likedCommentsUri;
    }

    public void setLikedCommentsUri(String likedCommentsUri) {
        this.likedCommentsUri = likedCommentsUri;
    }

    public String getDislikedCommentsUri() {
        return this.dislikedCommentsUri;
    }

    public void setDislikedCommentsUri(String dislikedCommentsUri) {
        this.dislikedCommentsUri = dislikedCommentsUri;
    }

    public String getBlockedUsersUri() {
        return this.blockedUsersUri;
    }

    public void setBlockedUsersUri(String blockedUsersUri) {
        this.blockedUsersUri = blockedUsersUri;
    }

    public String getProfilePicUri() {
        return this.profilePicUri;
    }

    public void setProfilePicUri(String profilePicUri) {
        this.profilePicUri = profilePicUri;
    }

    public String getCreatedTagsUri() {
        return this.createdTagsUri;
    }

    public void setCreatedTagsUri(String createdTagsUri) {
        this.createdTagsUri = createdTagsUri;
    }

    public String getFavTagsUri() {
        return this.favTagsUri;
    }

    public void setFavTagsUri(String favTagsUri) {
        this.favTagsUri = favTagsUri;
    }

    public String getFavCategoriesUri() {
        return this.favCategoriesUri;
    }

    public void setFavCategoriesUri(String favCategoriesUri) {
        this.favCategoriesUri = favCategoriesUri;
    }

    public String getMentionsUri() {
        return this.mentionsUri;
    }

    public void setMentionsUri(String mentionsUri) {
        this.mentionsUri = mentionsUri;
    }
    



    


    
}
