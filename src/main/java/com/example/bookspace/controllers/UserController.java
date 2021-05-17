package com.example.bookspace.controllers;

import java.util.List;
import java.util.Map;


import com.example.bookspace.Exceptions.IncorrectTokenException;
import com.example.bookspace.Exceptions.UserNotFoundException;
import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.Output.CommentOutput;
import com.example.bookspace.Output.MentionOutput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.services.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @GetMapping(path = "/token/{userId}")
    public Map<String, String> getToken(@PathVariable(name = "userId", required = true) Long id) throws Exception {
        return userService.getToken(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "The user has been posted")
    public UserOutput postUser(@RequestBody UserInput userDetails) throws Exception{
       return userService.postUser(userDetails);
    }

    @PostMapping(path = "/login")
    @ResponseBody
    public Map<String, String> loginUser(@RequestBody UserInput userDetails) throws Exception {
        return userService.loginUser(userDetails);
    }

    @PostMapping(path = "{userId}/logout")
    @ResponseStatus(value = HttpStatus.OK, reason = "The user has successfully logout")
    public void logoutUser(@PathVariable(name = "userId", required = true) Long userId, @RequestHeader(value = "auth", required = true) String token) throws Exception {
        userService.logout(userId, token);
    }
    
    @GetMapping(path = "{userId}")   
	public UserOutput getUserById(@PathVariable(name = "userId", required = true) Long id) {
        return userService.getUser(id);
    }

    @GetMapping(path = "/username/{username}")   
	public UserOutput getUserByUsername(@PathVariable("username") String username) throws Exception {
        return userService.getUserByUsername(username);
    }

    @PutMapping(path = "{userId}") 
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public UserOutput updateUser(@PathVariable(name = "userId", required = true) Long id, @RequestBody UserInput userDetails, @RequestHeader(value = "auth", required = true) String token) throws Exception{
        return userService.putUser(id, userDetails, token);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable(name = "userId", required = true) Long userId, @RequestHeader(value = "auth", required = true) String token) throws IncorrectTokenException, UserNotFoundException{  
        userService.deleteUser(userId, token);
    }

    @GetMapping(path = "{userId}/profilePic")
    public void getProfilePic(@PathVariable("userId") Long userId) throws Exception{
        userService.getProfilePic(userId);
    }

    @PostMapping(path = "{userId}/profilePic")
    public void postProfilePic(@PathVariable("userId") Long userId) throws Exception{
        userService.postProfilePic(userId);
    }

    @DeleteMapping(path = "{userId}/profilePic")
    public void deleteProfilePic(@PathVariable("userId") Long userId) throws Exception{
        userService.deleteProfilePic(userId);
    }

    @GetMapping(path = "{userId}/categories")   
	public List<String> getFavCategoriesUser(@PathVariable("userId") Long id) {
        return userService.getFavCategoriesUser(id);
    }

    @GetMapping (path = "{userId}/publications")
    public List<PublicationOutput> getPublicationsUser(@PathVariable("userId") Long id) {
        return userService.getPublicationsUser(id);
    }

    @GetMapping (path = "{userId}/likedPublications")
    public List<PublicationOutput> getLikedPublications(@PathVariable("userId") Long id) throws Exception {
        return userService.getLikedPublications(id);
    }

    @GetMapping (path = "{userId}/dislikedPublications")
    public List<PublicationOutput> getDislikedComments(@PathVariable("userId") Long id) throws Exception {
        return userService.getDislikedPublications(id);
    }

    @GetMapping (path = "{userId}/favPublications")
    public List<PublicationOutput> getFavPublications(@PathVariable("userId") Long id) throws Exception {
        return userService.getFavPublications(id);
    }

    @GetMapping (path = "{userId}/mentions")
    public List<MentionOutput> getMentions(@PathVariable("userId") Long id) throws Exception {

        return userService.getMentions(id);
    }

    @GetMapping (path = "{userId}/comments")
    public List<CommentOutput> getComments(@PathVariable("userId") Long id) throws Exception {
        return userService.getComments(id);
    }

    @GetMapping (path = "{userId}/likedComments")
    public List<CommentOutput> getLikedComments(@PathVariable("userId") Long id) throws Exception {
        return userService.getLikedComments(id);
    }

    @GetMapping (path = "{userId}/dislikedComments")
    public List<CommentOutput> getDislikedPublications(@PathVariable("userId") Long id) throws Exception {
        return userService.getDislikedComments(id);
    }

    @GetMapping(path = "{userId}/tags")   
	public List<TagOutput> getCreatedTags(@PathVariable("userId") Long id) throws Exception {
        return userService.getCreatedTags(id);
    }

    @GetMapping(path = "{userId}/favTags")   
	public List<TagOutput> getFavTagsUser(@PathVariable("userId") Long id) {
        return userService.getFavTagsUser(id);
    }

    @GetMapping(path = "{userId}/blockedUsers")   
	public List<UserOutput> getBlockedUsers(@PathVariable("userId") Long id) throws Exception {
        return userService.getBlockedUsers(id);
    }

    @PostMapping(path = "{userId}/blockedUsers/{blockedUserId}")   
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "User has been blocked")
	public UserOutput postBlockedUsers(@PathVariable(name = "userId", required = true) Long id, @PathVariable(name = "blockedUserId", required = true) Long blockedUserid, @RequestHeader(name = "auth", required = true) String token) throws Exception {
        return userService.postBlockedUsers(id, blockedUserid, token);
    }

    @DeleteMapping(path = "{userId}/blockedUsers/{blockedUserId}")   
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "User has been unblocked")
	public UserOutput deleteBlockedUser(@PathVariable(name = "userId", required = true) Long id, @PathVariable(name = "blockedUserId", required = true) Long blockedUserid, @RequestHeader(name = "auth", required = true) String token) throws Exception {
        return userService.deleteBlockedUsers(id, blockedUserid, token);
    }
 

 
  






    
    

    
}