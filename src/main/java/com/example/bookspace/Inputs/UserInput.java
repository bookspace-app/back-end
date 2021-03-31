package com.example.bookspace.Inputs;

import java.time.LocalDate;


public class UserInput {
    private String email;
    private String name;
    private String username;
    private LocalDate dob;
    private String description; 

    public UserInput(String email, String name, String username, LocalDate dob, String description) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.dob = dob;
        this.description = description;
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

     
    



}
