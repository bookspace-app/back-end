package com.example.bookspace.Inputs;

import java.time.LocalDate;
import java.util.List;


public class UserInput {
    private String email;
    private String name;
    private String username;
    private String password;
    private LocalDate dob;
    private String description;
    private List<String> favCategories;

    public UserInput() {}
    


    public UserInput(String email, String name, String username, String password, LocalDate dob, String description, List<String> favCategories) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.description = description;
        this.favCategories = favCategories;
    }

    
  
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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


    public List<String> getFavCategories() {
        return this.favCategories;
    }

    public void setFavCategories(List<String> favCategories) {
        this.favCategories = favCategories;
    }


    public Boolean validDescription() {
        return (this.description != null);
    }
}
