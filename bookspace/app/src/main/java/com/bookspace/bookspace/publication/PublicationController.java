package com.bookspace.bookspace.publication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String getPublication() {
        return "MS";
	}

    @PutMapping    
	public String putPublication() {
        return "MS";
	}

    @DeleteMapping  
	public String deletePublication() {
        return "MS";
	}
}
