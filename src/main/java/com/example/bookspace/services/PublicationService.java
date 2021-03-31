package com.example.bookspace.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.bookspace.models.Comment;
import com.example.bookspace.models.Publication;
import com.example.bookspace.models.User;
import com.example.bookspace.repositories.PublicationRepository;
import com.example.bookspace.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;;
    private final UserRepository userRepository;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository, UserRepository userRepository) {
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
    }

    public List<Publication> getPublications() {
        return publicationRepository.findAll();
    }

    public Optional<Publication> getPublication(Long id) {
        return publicationRepository.findById(id);
    }

    public Optional<Publication> addNewPublication(Publication p) {
        Publication publication = new Publication(p.getTitle(), p.getContent());
        publicationRepository.save(publication);
        return publicationRepository.findById(publication.getId());
    
    }

    @Transactional
	public void updatePublication(Long id, String title, String content) {
		Publication publication = publicationRepository.findById(id)
					.orElseThrow(() -> new IllegalStateException(
						"Publication with id " + id + " does not exist"));
		
                
        publication.setTitle(title);		
        publication.setContent(content);
        publicationRepository.save(publication);

	}

    public void deletePublication(Long publicationId){
		boolean b = publicationRepository.existsById(publicationId);
		if(!b) {
			throw new IllegalStateException("Publication with id " + publicationId + " does not exists");
		}
		publicationRepository.deleteById(publicationId);

	}

    public List<User> getVotedByUsers(Long id) {
        Publication p = publicationRepository.getOne(id);
        return p.getVotedBy();

    }

    public List<User> getFavouriteByUsers(Long id) {
        Publication p = publicationRepository.getOne(id);
        return p.getFavouriteBy();
    }

    public List<Comment> getComments(Long id) {
        Publication p = publicationRepository.getOne(id);
        return p.getComments();
    }

    public void addAuthor(Long publication_id, Long author_id) {
        Publication publication = publicationRepository.getOne(publication_id);
        User author = userRepository.getOne(author_id);
        publication.setAuthor(author);

    }

    public Publication assignAuthorToPublication(Long publicationId, Long authorId) {
        User author = userRepository.findById(authorId).get();
        Publication publication = publicationRepository.findById(publicationId).get();
        
        publication.setAuthor(author);
        author.addPublication(publication);

        publicationRepository.save(publication);
        userRepository.save(author);

        return publication;
        
        
    }   


    

    
}
