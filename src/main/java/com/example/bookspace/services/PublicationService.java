package com.example.bookspace.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.example.bookspace.Inputs.PublicationInput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.UserOutput;
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

     

    public List<PublicationOutput> getPublications() {
        List<PublicationOutput> result = new ArrayList<>();

        for (Publication p: publicationRepository.findAll()) {
            User author = userRepository.getOne(p.getAuthor().getId());
            PublicationOutput po = new PublicationOutput(p, new UserOutput(author));
            result.add(po);
        }

        return result;
    }

    public PublicationOutput getPublication(Long id) {
        Publication p = publicationRepository.getOne(id);
        User u = userRepository.getOne(p.getAuthor().getId());
        return new PublicationOutput(p, new UserOutput(u));
    }

    public PublicationOutput addNewPublication(PublicationInput publicationDetails) {
        User author = userRepository.findById(publicationDetails.getAuthor()).get();
        Publication publication = new Publication(publicationDetails.getTitle(), publicationDetails.getContent(), author, publicationDetails.getCategory());
        author.addPublication(publication);
        publicationRepository.save(publication);
        userRepository.save(author);
        return new PublicationOutput(publication, new UserOutput(author));
    
    }

    @Transactional
	public void updatePublication(Long id, PublicationInput publicationDetails) {
		Publication publication = publicationRepository.findById(id)
					.orElseThrow(() -> new IllegalStateException(
						"Publication with id " + id + " does not exist"));
		
                
        publication = new Publication(publicationDetails);
        User author = userRepository.getOne(publicationDetails.getAuthor());
        publication.setAuthor(author);
        publicationRepository.save(publication);

	}

    public void deletePublication(Long publicationId){
		boolean b = publicationRepository.existsById(publicationId);
		if(!b) {
			throw new IllegalStateException("Publication with id " + publicationId + " does not exists");
		}
		publicationRepository.deleteById(publicationId);

	}



    public List<UserOutput> getVotedByUsers(Long id) {
        Publication p = publicationRepository.getOne(id);
        List<UserOutput> result = new ArrayList<>();

        for (User u: p.getVotedBy()) {
            result.add(new UserOutput(u));
        }
        return result;
    }



    public List<UserOutput> getFavUsers(Long id) {
        Publication p = publicationRepository.getOne(id);
        List<UserOutput> result = new ArrayList<>();
        for (User u: p.getFavouriteBy()) {
            result.add(new UserOutput(u));
        }

        return result;
    }



    public UserOutput postFavUser(Long id, Long userId) {
        Publication p = publicationRepository.getOne(id);
        User favUser = userRepository.getOne(userId);

        p.addFavUser(favUser);
        favUser.addFavPublication(p);

        publicationRepository.save(p);
        userRepository.save(favUser);

        return new UserOutput(favUser);

    }



    // public List<CommentOutput> getComments(Long id) {
    //     Publication p = publicationRepository.getOne(id);
    //     List<CommentOutput> result = new ArrayList<>();

    //     for (User u: p.getComments()) {
    //         result.add(new CommentOutput(u));
    //     }
    //     return result;
    // }

    



    

    
}
