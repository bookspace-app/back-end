package com.example.bookspace.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.bookspace.models.Comment;
import com.example.bookspace.models.Publication;
import com.example.bookspace.models.User;
import com.example.bookspace.services.PublicationService;

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
@RequestMapping(path = "api/publications")

public class PublicationController {
    
    private final PublicationService publicationService;

    @Autowired
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping    
	public List<Publication> getAllPublications() {
        return publicationService.getPublications();
    }

    @PostMapping
    public void registerNewPublication(@RequestBody Publication p) {
        publicationService.addNewPublication(p);
    }
    
    @GetMapping(path = "{publicationId}")
    public Optional<Publication> getUserById(@PathVariable("publicationId") Long id) {
        return publicationService.getPublication(id);
    }    

    @PutMapping(path = "{publicationId}")
    public void putPublication(@PathVariable("publicationId")Long id,
                                                @RequestBody Publication publicationDetails) {
        publicationService.updatePublication(publicationDetails);
	}

    @DeleteMapping(path = "{publicationId}")
	public Boolean deletePublication(@PathVariable("publicationId") Long publicationId) {
        return publicationService.deletePublication(publicationId);
	}

    @GetMapping(path="{publicationId}/votedBy")
    public Set<User> getVotedByUsers (@PathVariable("publicationId") Long id) {
        return publicationService.getVotedByUsers(id);
    }

    @GetMapping(path="{publicationId}/favouritesBy")
    public Set<User> getFavouriteByUsers(@PathVariable("publicationId") Long id) {
        return publicationService.getFavouriteByUsers(id);
    }

    @GetMapping(path="{publicationId}/comments")
    public Set<Comment> getPublicationComments(@PathVariable("publicationId") Long id) {
        return publicationService.getComments(id);
    }
}
