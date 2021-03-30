package com.example.bookspace.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import com.example.bookspace.models.Comment;
import com.example.bookspace.models.Publication;
import com.example.bookspace.models.User;
import com.example.bookspace.repositories.PublicationLightRepository;
import com.example.bookspace.repositories.PublicationRepository;
import com.example.bookspace.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;;
    private final PublicationLightRepository publicationLightRepository;
    private final UserRepository userRepository;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository, PublicationLightRepository publicationLightRepository, UserRepository userRepository) {
        this.publicationRepository = publicationRepository;
        this.publicationLightRepository = publicationLightRepository;
        this.userRepository = userRepository;
    }

    public List<Publication> getPublications() {
        return publicationRepository.findAll();
    }

    public Optional<Publication> getPublication(Long id) {
        return publicationRepository.findById(id);
    }

    public void addNewPublication(Publication publication) {
        User author = userRepository.getOne(publication.getAuthor().getId());
        Publication p = new Publication(publication.getTitle(), publication.getContent(), author);
        publicationRepository.save(p);
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

    public Set<User> getVotedByUsers(Long id) {
        Publication p = publicationRepository.getOne(id);
        return p.getVotedBy();

    }

    public Set<User> getFavouriteByUsers(Long id) {
        Publication p = publicationRepository.getOne(id);
        return p.getFavouriteBy();
    }

    public Set<Comment> getComments(Long id) {
        Publication p = publicationRepository.getOne(id);
        return p.getComments();
    }   


    

    
}
