package com.example.bookspace.controllers;

import java.util.List;

import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<UserOutput> getAllUsers(){
        return userService.getUsers();
    }

    
    @GetMapping(path = "{userId}")   
	public UserOutput getUserById(@PathVariable("userId") Long id) {
        return userService.getUser(id);
    }

    @GetMapping (path = "{userId}/publications")
    public List<PublicationOutput> getPublicationsUser(@PathVariable("userId") Long id) {
        return userService.getPublicationsUser(id);
    }

    @GetMapping(path = "{userId}/preferedTags")   //Implementar
	public List<TagOutput> getPreferedTagsUser(@PathVariable("userId") Long id) {
        return userService.getPreferedTagsUser(id);
    }

    @PostMapping
    public UserOutput postUser(@RequestBody UserInput userDetails){
       return userService.postUser(userDetails);
    }

    @PutMapping(path = "{userId}") 
    public UserOutput updateUser(@PathVariable("userId") Long id,
                                       @RequestBody UserInput u){
        return userService.updateUser(id,u);
    }

    // @PutMapping(path = "{userId}/preferedTags") 
    // public UserOutput addPreferedTags(@PathVariable("userId") Long id,
    //                                    @RequestBody UserInput u){
    //     return userService.updateUser(id,u);
    // }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }



    
    

    
}
