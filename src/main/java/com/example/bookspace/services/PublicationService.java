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
import com.example.bookspace.models.Comment;
import com.example.bookspace.models.Publication;
import com.example.bookspace.models.Tag;
import com.example.bookspace.models.User;
import com.example.bookspace.repositories.PublicationRepository;
import com.example.bookspace.repositories.TagRepository;
import com.example.bookspace.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
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
            if (publicationDetails.getTags() != null) {
                for (Long tagId: publicationDetails.getTags()) {
                    Tag tag = tagRepository.getOne(tagId);
                    publication.addTag(tag);
                    tag.addPublication(publication);
                    tag = tagRepository.save(tag);
                }
            }

            if (publicationDetails.getMentions() != null) {
                for (Long mentionId: publicationDetails.getMentions()) {
                    User mention = userRepository.getOne(mentionId);
                    publication.addMention(mention);
                    mention.addMention(publication);
                    mention = userRepository.save(mention);
                }
            }
            author.addPublication(publication);
            publication = publicationRepository.save(publication);
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
	public PublicationOutput putPublication(Long id, PublicationInput publicationDetails) throws Exception {
		Publication publication = publicationRepository.findById(id)
					.orElseThrow(() -> new IllegalStateException(
						"Publication with id " + id + " does not exist"));
		
        
        if (publicationDetails.getTitle() != null) publication.setTitle(publicationDetails.getTitle());
        else if (publicationDetails.getContent() != null) publication.setContent(publicationDetails.getContent());
        else if (publicationDetails.getCategory() != null) {
            if (!Category.existsCategory(publicationDetails.getCategory())) throw new Exception("The Category " + publicationDetails.getCategory() + " does not exist");
            Category c = Category.getCategory(publicationDetails.getCategory());
            publication.setCategory(c);
        } 
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
        List<UserOutput> result = new ArrayList<>();
        Publication p = publicationRepository.getOne(publicationId);
        for (User u: p.getLikedBy()) {
            result.add(new UserOutput(u));
        }   
        return result;
    }

    public PublicationOutput postLike(Long publicationId, Long userId) throws Exception {
        Publication p = publicationRepository.getOne(publicationId);
        User u = userRepository.getOne(userId);
        if (p.getLikedBy().contains(u)) throw new Exception("This user has already liked this publication");

        if (p.getDislikedBy().contains(u))  {
            p.getDislikedBy().remove(u);
            u.getLikedPublications().remove(p); 
        }

        p.addLikedUser(u);
        p = publicationRepository.save(p);
        u.addLikedPublication(p);
        u =userRepository.save(u);

        return new PublicationOutput(p);


    }
    
    public PublicationOutput deleteLike(Long publicationId, Long userId) throws Exception {
        Publication p = publicationRepository.getOne(publicationId);
        User u = userRepository.getOne(userId);
        if (!p.getLikedBy().contains(u)) throw new Exception ("This user has not liked this publication");

        p.getLikedBy().remove(u);
        u.getLikedPublications().remove(p);
        u = userRepository.save(u);
        p = publicationRepository.save(p);
        return new PublicationOutput(p);
        
    }    
    
    public List<UserOutput> getDislikedUsers(Long publicationId) throws Exception {
       Publication p = publicationRepository.getOne(publicationId);
       List<UserOutput> result = new ArrayList<>();
       for (User u: p.getDislikedBy()) {
           result.add(new UserOutput(u));
       }
       return result;
    }



	public PublicationOutput postDislike(Long publicationId, Long userId) throws Exception {
        Publication p = publicationRepository.getOne(publicationId);
        User u = userRepository.getOne(userId);
        if (p.getDislikedBy().contains(u)) throw new Exception("This user has already disliked this publication");

        if (p.getLikedBy().contains(u))  {
            p.getLikedBy().remove(u);
            u.getLikedPublications().remove(p); 
        }

        p.addDislikedUser(u);
        p = publicationRepository.save(p);
        u.addDislikedPublication(p);
        userRepository.save(u);

        return new PublicationOutput(p);	}



	public PublicationOutput deleteDislike(Long publicationId, Long userId) throws Exception {
        Publication p = publicationRepository.getOne(publicationId);
        User u = userRepository.getOne(userId);
        if (!p.getDislikedBy().contains(u)) throw new Exception ("This user has not disliked this publication");

        p.getDislikedBy().remove(u);
        u.getDislikedPublications().remove(p);
        u = userRepository.save(u);
        p = publicationRepository.save(p);
        return new PublicationOutput(p);
	}
    
    
    public List<UserOutput> getFavUsers(Long id) {
        Publication p = publicationRepository.getOne(id);
        List<UserOutput> result = new ArrayList<>();
        for (User u: p.getFavouriteBy()) {
            result.add(new UserOutput(u));
        }

        return result;
    }



    public UserOutput postFavUser(Long id, Long userId) throws Exception {
        Publication p = publicationRepository.getOne(id);
        User favUser = userRepository.getOne(userId);
        if (p.getFavouriteBy().contains(favUser)) throw new Exception("This user has already faved this publication");
        
        p.addFavUser(favUser);
        favUser.addFavPublication(p);

        p = publicationRepository.save(p);
        favUser = userRepository.save(favUser);

        return new UserOutput(favUser);

    }

    public UserOutput deleteFavUser(Long id, Long userId) throws Exception {
        Publication p = publicationRepository.getOne(id);
        User favUser = userRepository.getOne(userId);
        if (!p.getFavouriteBy().contains(favUser)) throw new Exception("This user has not faved this publication");
        
        p.removeFavUser(favUser);
        favUser.removeFavPublication(p);

        p = publicationRepository.save(p);
        favUser = userRepository.save(favUser);

        return new UserOutput(favUser);    }

    public List<CommentOutput> getComments(Long publicationId) throws Exception {
        Publication p = publicationRepository.getOne(publicationId);
        List<CommentOutput> result = new ArrayList<>();
        for (Comment c: p.getComments()) {
            result.add(new CommentOutput(c));
        }

        return result;
    }



    public List<UserOutput> getMentions(Long publicationId) throws Exception {
        Publication publication = publicationRepository.getOne(publicationId);
        List<UserOutput> result = new ArrayList<>();
        for(User mention: publication.getMentions()) {
            result.add(new UserOutput(mention));
        }

        return result;
    }



    public List<TagOutput> getTags(Long publicationId) throws Exception {
        Publication p = publicationRepository.getOne(publicationId);
        List<TagOutput> result = new ArrayList<>();
        for (Tag t: p.getTags()) {
            result.add(new TagOutput(t));
        }

        return result;       

    }




    




   



    



   










    

    

    
}
