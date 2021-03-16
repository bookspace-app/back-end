package com.bookspace.bookspace.publication;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PublicationService {

    public List<Publication> getPublication() {
        return List.of(new Publication());
    }

    public Boolean putPublication(Publication publicationDetails) {
        return null;
        //return BD.instertPublication(publicationDetails);
        
    }

    public Boolean deletePublication(Publication publicationDetails) {
        return null;
        //return BD.deletePublication(publicationDetails);
    }
}
