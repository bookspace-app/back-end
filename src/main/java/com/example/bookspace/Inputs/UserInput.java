package com.example.bookspace.Inputs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.bookspace.models.Tag;





public class UserInput {
    private String email;
    private String name;
    private String username;
    private String password;
    private LocalDate dob;
    private String description;
    private byte[] profile_pic;


    // public UserInput(){}

    // public UserInput(String email, String password, String name, String username, LocalDate dob, int age, byte[] profile_pic, String description, LocalDate dor, List<Tag> preferedTags) {
    //     this.email = email;
    //     this.name = name;
    //     this.password = password;
    //     this.username = username;
    //     this.dob = dob;
    //     this.age = age;
    //     this.profile_pic = profile_pic;
    //     this.description = description;
    //     this.dor = dor;
    //     this.preferedTags = preferedTags;
    // }

    public UserInput(String email, String name, String username, String password){
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;
        this.dob = LocalDate.of(2000, 12, 2);

 

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



 
    



}
