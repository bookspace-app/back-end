package com.example.bookspace.Inputs;

import java.time.LocalDate;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> development


public class UserInput {
    private String email;
    private String name;
    private String username;
    private LocalDate dob;
<<<<<<< HEAD
    private String description; 

    public UserInput(String email, String name, String username, LocalDate dob, String description) {
=======
    private String description;
    private List<String> favCategories;


    public UserInput(String email, String name, String username, String password, LocalDate dob, String description, List<String> favCategories) {
>>>>>>> development
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.description = description;
<<<<<<< HEAD
    }


=======
        this.favCategories = favCategories;
    }

    
  
>>>>>>> development
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

<<<<<<< HEAD
    public void setDescription(String description) {
        this.description = description;
    }

     
    
=======
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
   



>>>>>>> development



}
