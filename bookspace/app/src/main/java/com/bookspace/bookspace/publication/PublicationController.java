package com.bookspace.bookspace.publication;

import java.util.List;
import java.util.Optional;

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
@RequestMapping(path = "api/v1/publications")

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
    
    @GetMapping(path = "{publicationId}")
    public Optional<Publication> getUserById(@PathVariable("publicationId") Long id) {
        return publicationService.getPublication(id);
    }

    @PostMapping
    public void registerNewPublication(@RequestBody Publication publication) {
        publicationService.addNewPublication(publication);
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
}
