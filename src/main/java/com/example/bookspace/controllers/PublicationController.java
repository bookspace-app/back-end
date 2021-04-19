package com.example.bookspace.controllers;

import java.util.List;

import com.example.bookspace.Inputs.PublicationInput;
import com.example.bookspace.Output.CommentOutput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.Output.UserOutput;

import com.example.bookspace.services.PublicationService;
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
@RequestMapping(path = "api/publications")

public class PublicationController {
    
    private final PublicationService publicationService;

    @Autowired
    public PublicationController(PublicationService publicationService, UserService userService) {
        this.publicationService = publicationService;
    }

    @GetMapping
	public List<PublicationOutput> getPublications() {
        return publicationService.getPublications();
    }


    @PostMapping
    public PublicationOutput postPublication(@RequestBody PublicationInput publicationDetails) throws Exception {
        return publicationService.postPublication(publicationDetails);
    }


    @GetMapping(path = "{publicationId}")
    public PublicationOutput getPublicationById(@PathVariable("publicationId") Long id) {
        return publicationService.getPublication(id);
    }    

    @PutMapping(path = "{publicationId}") 
    public PublicationOutput updatePublication(@PathVariable("publicationId") Long id, @RequestBody PublicationInput publicationDetails){
        return publicationService.putPublication(id, publicationDetails);
    }

    @DeleteMapping(path = "{publicationId}")
	public void deletePublication(@PathVariable("publicationId") Long publicationId) {
        publicationService.deletePublication(publicationId);
	}

    @GetMapping("{publicationId}/like")
    public List<UserOutput> getLikedUsers(@PathVariable("publicationId") Long publicationId) throws Exception {
        return publicationService.getLikedUsers(publicationId);
    }

    @PostMapping("{publicationId}/like/{userId}")
    public PublicationOutput postLikedUsers(@PathVariable("publicationId") Long publicationId, @PathVariable("userId") Long userId) throws Exception {
        return publicationService.postLike(publicationId, userId);
    }

    @DeleteMapping("{publicationId}/like/{userId}")
    public PublicationOutput deleteLikedUsers(@PathVariable("publicationId") Long publicationId, @PathVariable("userId") Long userId) throws Exception {
        return publicationService.deleteLike(publicationId, userId);
    }

    @GetMapping("{publicationId}/dislike")
    public List<UserOutput> getDislikedUsers(@PathVariable("publicationId") Long publicationId) throws Exception {
        return publicationService.getDislikedUsers(publicationId);
    }

    @PostMapping("{publicationId}/dislike/{userId}")
    public PublicationOutput postDislikedUsers(@PathVariable("publicationId") Long publicationId, @PathVariable("userId") Long userId) throws Exception {
        return publicationService.postDislike(publicationId, userId);
    }

    @DeleteMapping("{publicationId}/dislike/{userId}")
    public PublicationOutput deleteDislikedUsers(@PathVariable("publicationId") Long publicationId, @PathVariable("userId") Long userId)  throws Exception {
        return publicationService.deleteDislike(publicationId, userId);
    }

    @GetMapping("{publicationId}/fav")
    public List<UserOutput> getFavUsers(@PathVariable("publicationId") Long id) {
        return publicationService.getFavUsers(id);
        
    }

    @PostMapping("{publicationId}/fav/{userId}")
    public UserOutput postFaUser(@PathVariable("publicationId") Long id, @PathVariable("userId") Long userId) throws Exception {
        return publicationService.postFavUser(id, userId);
        
    }

    @DeleteMapping("{publicationId}/fav/{userId}")
    public UserOutput deleteFavUser(@PathVariable("publicationId") Long id, @PathVariable("userId") Long userId) throws Exception {
        return publicationService.deleteFavUser(id, userId);
        
    }

    @GetMapping("{publicationId}/comments")
    public List<CommentOutput> getComments(@PathVariable("publicationId") Long id) throws Exception {
        return publicationService.getComments(id);
        
    }

    @GetMapping("{publicationId}/mentions")
    public List<UserOutput> getMentions(@PathVariable("publicationId") Long id) throws Exception {
        return publicationService.getMentions(id);
    }

    @GetMapping("{publicationId}/tags")
    public List<TagOutput> getTags(@PathVariable("publicationId") Long id) throws Exception {
        return publicationService.getTags(id);
    }



    


   
}
