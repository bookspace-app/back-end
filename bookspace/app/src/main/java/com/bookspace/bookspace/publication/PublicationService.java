package com.bookspace.bookspace.publication;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public List<Publication> getPublications() {
        return publicationRepository.findAll();
    }

    public Optional<Publication> getPublication(Long id) {
        if (publicationRepository.existsById(id)) throw new IllegalStateException("It already exists a publication with this id");
        return publicationRepository.findById(id);
    }

    public void addNewPublication(Publication publication) {
        publicationRepository.save(publication);
    }

    @Transactional
    public void updatePublication(Publication publicationDetails) {
        Publication publication = publicationRepository.findById(publicationDetails.getId())
					.orElseThrow(() -> new IllegalStateException(
						"Publication with id " + publicationDetails.getId() + " does not exist"));
        
        publication = publicationDetails;
        publicationRepository.save(publication);     
        
        

    }

    public Boolean deletePublication(Long publicationId) {
        if (!publicationRepository.existsById(publicationId)) {
            publicationRepository.deleteById(publicationId);
            return true;
        }
        else throw new IllegalStateException("Id: " + publicationId + " does not belong to any publication");
        

    }


    

    
}
