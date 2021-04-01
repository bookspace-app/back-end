package com.example.bookspace.controllers;

import java.util.List;

import com.example.bookspace.Inputs.CommentInput;
import com.example.bookspace.Inputs.PublicationInput;
import com.example.bookspace.Output.CommentOutput;
import com.example.bookspace.Output.PublicationOutput;
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
	public List<PublicationOutput> getAllPublications() {
        return publicationService.getPublications();
    }


    @PostMapping
    public PublicationOutput postPublication(@RequestBody PublicationInput publicationDetails) {
        return publicationService.postPublication(publicationDetails);
    }


    @GetMapping(path = "{publicationId}")
    public PublicationOutput getUserById(@PathVariable("publicationId") Long id) {
        return publicationService.getPublication(id);
    }    

    @PutMapping(path = "{publicationId}") 
    public void updatePublication(@PathVariable("publicationId") Long id,
                                      @RequestParam PublicationInput publicationDetails
                                      ) throws Exception{
        publicationService.updatePublication(id, publicationDetails);
    }

    @DeleteMapping(path = "{publicationId}")
	public void deletePublication(@PathVariable("publicationId") Long publicationId) {
        publicationService.deletePublication(publicationId);
	}

    @GetMapping("{publicationId}/favUsers")
    public List<UserOutput> getFavUsers(@PathVariable("publicationId") Long id) {
        return publicationService.getFavUsers(id);
        
    }

    @PostMapping("{publicationId}/favUsers/{userId}")
    public UserOutput postFaUser(@PathVariable("publicationId") Long id, @PathVariable("userId") Long userId) {
        return publicationService.postFavUser(id, userId);
        
    }

    @PostMapping(path = "{publicationId}/likes")
    public PublicationOutput postLike(@PathVariable("publicationId") Long id) {
        return publicationService.postLike(id);
    }

    @PostMapping(path = "{publicationId}/dislikes")
    public PublicationOutput postDislike(@PathVariable("publicationId") Long id) {
        return publicationService.postDislike(id);
    }
  
    @GetMapping(path="{publicationId}/comments")
    public List<CommentOutput> getPublicationComments(@PathVariable("publicationId") Long id) {
        return publicationService.getComments(id);
    }

    @PostMapping(path="{publicationId}/comments")
    public CommentOutput postPublicationComment(@PathVariable("publicationId") Long id,
                                                @RequestBody CommentInput commentDetails) {
            return publicationService.postComment(id, commentDetails);

    }
}
