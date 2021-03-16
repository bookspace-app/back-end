package com.bookspace.bookspace.publication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/publication")

public class PublicationController {
    
    private final PublicationService publicationService;

    @Autowired
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping    
	public List<Publication> getPublication() {
        return publicationService.getPublication();
	}

    @PutMapping
	public Boolean putPublication(@RequestBody Publication publicationDetails) {
        return publicationService.putPublication(publicationDetails);
	}

    @DeleteMapping
	public Boolean deletePublication(@RequestBody Publication publicationDetails) {
        return publicationService.deletePublication(publicationDetails);
	}
}
