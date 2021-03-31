package com.example.bookspace.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.bookspace.models.User;
import com.example.bookspace.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/users")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getUsers();
    }

    
    @GetMapping(path = "{userId}")   
	public Optional<User> getUserById(@PathVariable("userId") Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @PutMapping(path = "{userId}") 
    public void updateUser(@PathVariable("userId") Long id,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) String description,
                                      @RequestParam(required = false) String email,
                                      @RequestParam(required = false) String username,
                                      @RequestParam(required = false) LocalDate dob){
        userService.updateUser(id,name,description,email,username,dob);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }



    
    

    
}
