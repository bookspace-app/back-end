package com.example.bookspace.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.Output.CommentOutput;
import com.example.bookspace.Output.MentionOutput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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


    @PostMapping
    public UserOutput postUser(@RequestBody UserInput userDetails) throws Exception{
       return userService.postUser(userDetails);
    }
    
    @GetMapping(path = "{userId}")   
	public UserOutput getUserById(@PathVariable("userId") Long id) {
        return userService.getUser(id);
    }

    @GetMapping(path = "/username/{username}")   
	public UserOutput getUserByUsername(@PathVariable("username") String username) throws Exception {
        return userService.getUserByUsername(username);
    }

    @PutMapping(path = "{userId}") 
    public UserOutput updateUser(@PathVariable("userId") Long id, @RequestBody UserInput userDetails) throws Exception{
        return userService.putUser(id, userDetails);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }

    @GetMapping(path = "{userId}/profilePic")
    public String getProfilePic(@PathVariable("userId") Long userId) throws Exception{
       //System.out.println(userService.getProfilePic(userId));
        return userService.getProfilePic(userId);
    }

    @PostMapping(path = "{userId}/profilePic")
    public UserOutput postProfilePic(@PathVariable("userId") Long userId, @RequestParam("profilePic") MultipartFile profilePic) throws Exception{
         String fileName = StringUtils.cleanPath(profilePic.getOriginalFilename());
 
         String uploadDir = "./user-images/" + userId;
         Path uploadPath = Paths.get(uploadDir);
         if(!Files.exists(uploadPath)){
             Files.createDirectories(uploadPath);
         }
 
         try (InputStream inputStream = profilePic.getInputStream()){
             Path filePath = uploadPath.resolve(fileName);
             System.out.println(filePath.toFile().getAbsolutePath());
             Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
         } catch (IOException e){
             throw new IOException("Could not save uploaded file: " + fileName);
         }
         return userService.postProfilePic(userId,fileName);

        
    }

    @DeleteMapping(path = "{userId}/profilePic")
    public UserOutput deleteProfilePic(@PathVariable("userId") Long userId) throws Exception{
        return userService.deleteProfilePic(userId);
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
	public UserOutput postBlockedUsers(@PathVariable("userId") Long id, @PathVariable("blockedUserId") Long blockedUserid) throws Exception {
        return userService.postBlockedUsers(id, blockedUserid);
    }

    @DeleteMapping(path = "{userId}/blockedUsers/{blockedUserId}")   
	public void deleteBlockedUsers(@PathVariable("userId") Long id, @PathVariable("blockedUserId") Long blockedUserid) throws Exception {
        userService.deleteBlockedUsers(id, blockedUserid);
    }

    @GetMapping(path = "{userId}/profilePicPath")   
	public String getProfilePicPath(@PathVariable("userId") Long id) throws Exception {
        return userService.getProfilePicPath(id);
    }


 

 
  






    
    

    
}