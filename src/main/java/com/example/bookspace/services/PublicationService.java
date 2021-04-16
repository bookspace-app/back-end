package com.example.bookspace.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.example.bookspace.Inputs.PublicationInput;
import com.example.bookspace.Output.CommentOutput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.enums.Category;
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
            PublicationOutput po = new PublicationOutput(p);
            result.add(po);
        }

        return result;
    }

    public PublicationOutput postPublication(PublicationInput publicationDetails) throws Exception {
        if (publicationDetails.getTitle() == null) throw new Exception("The title can't be empty");
        else if (publicationDetails.getContent() == null) throw new Exception("The content can't be empty");
        else if (publicationDetails.getAuthorId() == null) throw new Exception("The author_id can't be empty");
        else if (!userRepository.existsById(publicationDetails.getAuthorId())) throw new Exception("There are no users with this id");
        else if (publicationDetails.getCategory() == null) throw new Exception("The category can't be empty");
        else if (!Category.existsCategory(publicationDetails.getCategory())) throw new Exception ("There are not categories with that name");
        else {
            User author = userRepository.findById(publicationDetails.getAuthorId()).get();
            Category category = Category.getCategory(publicationDetails.getCategory());
            Publication publication = new Publication(publicationDetails.getTitle(), publicationDetails.getContent(), author, category);
            author.addPublication(publication);
            publicationRepository.save(publication);
            userRepository.save(author);
            return new PublicationOutput(publication);
        }
            
    }
    
    public PublicationOutput getPublication(Long id) {
        Publication p = publicationRepository.getOne(id);
        p.addView();
        publicationRepository.save(p);
        return new PublicationOutput(p);
    }

    @Transactional
	public PublicationOutput putPublication(Long id, PublicationInput publicationDetails) {
		Publication publication = publicationRepository.findById(id)
					.orElseThrow(() -> new IllegalStateException(
						"Publication with id " + id + " does not exist"));
		
        
        if (publicationDetails.getTitle() != null) publication.setTitle(publicationDetails.getTitle());
        if (publicationDetails.getContent() != null) publication.setContent(publicationDetails.getContent());
        publication = publicationRepository.save(publication);
        return new PublicationOutput(publication);

	}

    public void deletePublication(Long publicationId){
		boolean b = publicationRepository.existsById(publicationId);
		if(!b) {
			throw new IllegalStateException("Publication with id " + publicationId + " does not exists");
		}
		publicationRepository.deleteById(publicationId);

	}


    public List<UserOutput> getLikedUsers(Long publicationId) throws Exception {
		throw new Exception("This endpoint is not implemented yet");
    }

    public PublicationOutput postLike(Long publicationId) throws Exception {
		throw new Exception("This endpoint is not implemented yet");
    }
    
    public PublicationOutput deleteLike(Long publicationId) throws Exception {
        throw new Exception("This endpoint is not implemented yet");

    }    
    
    public List<UserOutput> getDislikedUsers(Long publicationId) throws Exception {
		throw new Exception("This endpoint is not implemented yet");
    }



	public PublicationOutput postDislike(Long publicationId) throws Exception {
		throw new Exception("This endpoint is not implemented yet");
	}



	public void deleteDislike(Long publicationId) throws Exception {
        throw new Exception("This endpoint is not implemented yet");

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
        favUser.addLikedPublication(p);

        p = publicationRepository.save(p);
        favUser = userRepository.save(favUser);

        return new UserOutput(favUser);

    }

    public UserOutput deleteFavUser(Long id, Long userId) throws Exception {
        throw new Exception("This endpoint is not implemented yet");
    }

    public List<CommentOutput> getComments(Long id) throws Exception {
        throw new Exception("This endpoint is not implemented yet");
    }



    public List<UserOutput> getMentions(Long id) throws Exception {
        throw new Exception("This endpoint is not implemented yet");
    }



    public List<TagOutput> getTags(Long id) throws Exception {
        throw new Exception("This endpoint is not implemented yet");
    }




    




   



    



   










    

    

    
}
