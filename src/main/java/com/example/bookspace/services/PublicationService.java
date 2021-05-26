package com.example.bookspace.services;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import com.example.bookspace.Exceptions.ActionNotPermited;
import com.example.bookspace.Exceptions.BadRequestPublicationException;
import com.example.bookspace.Exceptions.CategoryNotFoundException;
import com.example.bookspace.Exceptions.DuplicateActionException;
import com.example.bookspace.Exceptions.IncorrectTokenException;
import com.example.bookspace.Exceptions.LoginException;
import com.example.bookspace.Exceptions.PublicationNotFound;
import com.example.bookspace.Exceptions.UserNotFoundException;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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

     

    public List<PublicationOutput> getPublications(String sortString)  {
        List<PublicationOutput> result = new ArrayList<>();
        List<Publication> publications = publicationRepository.findAll();

        Comparator<Publication> compareByViews = new Comparator<Publication>() {
            @Override
            public int compare(Publication p1, Publication p2) {
                return p1.getViews().compareTo(p2.getViews());
            }
        };

        Comparator<Publication> compareByLikes = new Comparator<Publication>() {
            @Override
            public int compare(Publication p1, Publication p2) {
                return p1.getTotalLikes().compareTo(p2.getTotalLikes());
            }
        };
       
        if (Category.existsCategory(sortString)) {
            publications = publicationRepository.findByCategory(Category.getCategory(sortString));
        }
           
        else if (sortString.equals("views")) {
            Collections.sort(publications, compareByViews.reversed());
        }

        else if (sortString.equals("score")) {
            Collections.sort(publications, compareByLikes.reversed());
        }      


        for (Publication p: publications) {
            PublicationOutput po = new PublicationOutput(p);
            result.add(po);
        }

        return result;
    }

    public PublicationOutput postPublication(PublicationInput publicationDetails, String token) throws UserNotFoundException {

        
        if (publicationDetails.getTitle() == null) throw new BadRequestPublicationException("The title can't be empty");
        else if (publicationDetails.getContent() == null) throw new BadRequestPublicationException("The content can't be empty");
        else if (publicationDetails.getAuthorId() == null) throw new BadRequestPublicationException("The authorId can't be empty");
        else if (!userRepository.existsById(publicationDetails.getAuthorId())) throw new UserNotFoundException(publicationDetails.getAuthorId());
        else if (publicationDetails.getCategory() == null) throw new BadRequestPublicationException("The category can't be empty");
        else if (!Category.existsCategory(publicationDetails.getCategory())) throw new CategoryNotFoundException (publicationDetails.getCategory());
        else {

                User author = userRepository.getOne(publicationDetails.getAuthorId());

                if (author.getToken() == null) throw new LoginException();
                if (!author.getToken().equals(token)) throw new IncorrectTokenException();

                Category category = Category.getCategory(publicationDetails.getCategory());

                Publication publication = new Publication(publicationDetails.getTitle(), publicationDetails.getContent(), author, category);

                //Boolean enoughRank = true;
                if (publicationDetails.getTags() != null) {
                    for (String tagName: publicationDetails.getTags()) {
                        if (tagRepository.findTagByName(tagName).isPresent()) {
                            Tag tag = tagRepository.getTagByName(tagName);
                            tag.getPublications().add(publication);
                            tag = tagRepository.save(tag);
                            publication.addTag(tag);
                            author.addFavTag(tag); 

                        }
                        else {
                            if (author.canCreateTags()){
                                Tag tag = new Tag(tagName, author);
                                tag.getPublications().add(publication);
                                author.addCreatedTag(tag); 
                                tag = tagRepository.save(tag);
                                publication.addTag(tag);
                                author.addFavTag(tag); 
                            }
                            //else enoughRank = false;
                           
                        }
                        


                    }
                }

                if (publicationDetails.getMentions() != null) {
                    for (String username: publicationDetails.getMentions()) {
                        if(userRepository.findUserByUsername(username).isPresent()) {
                            User user = userRepository.getUserByUsername(username);
                            publication.addMention(user);
                            user.addMention(publication);
                            user = userRepository.save(user);
                        }
                    }
                }

                author.addPublication(publication);
                publication = publicationRepository.save(publication);
                author = userRepository.save(author);

                //if (!enoughRank) throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Can't create more tags with rank: [" + author.getRank().name() + "]");


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
	public PublicationOutput putPublication(Long id, PublicationInput publicationDetails, String token)  {

        User author = userRepository.getOne(publicationDetails.getAuthorId());

        if (author.getToken() == null) throw new LoginException();
        if (!author.getToken().equals(token)) throw new IncorrectTokenException();

		if (!publicationRepository.existsById(id)) throw new PublicationNotFound(id);
		
        Publication publication = publicationRepository.getOne(id);

                
        if (publicationDetails.getTitle() != null) publication.setTitle(publicationDetails.getTitle());
        if (publicationDetails.getContent() != null) publication.setContent(publicationDetails.getContent());
        if (publicationDetails.getCategory() != null) {

            if (!Category.existsCategory(publicationDetails.getCategory())) throw new CategoryNotFoundException(publicationDetails.getCategory());
            
            Category c = Category.getCategory(publicationDetails.getCategory());
            publication.setCategory(c);
        } 
    
        publication = publicationRepository.save(publication);
        return new PublicationOutput(publication);

	}

    public void deletePublication(Long publicationId, String token) throws LoginException{

        if (!publicationRepository.existsById(publicationId)) throw new PublicationNotFound(publicationId);
		
        Publication publication = publicationRepository.getOne(publicationId);
        User author = userRepository.getOne(publication.getAuthor().getId());

        if (author.getToken() == null) throw new LoginException();
        if (!author.getToken().equals(token)) throw new IncorrectTokenException();


		publicationRepository.deleteById(publicationId);

	}


    public List<UserOutput> getLikedUsers(Long publicationId)  {
        List<UserOutput> result = new ArrayList<>();
        Publication p = publicationRepository.getOne(publicationId);
        for (User u: p.getLikedBy()) {
            result.add(new UserOutput(u));
        }   
        return result;
    }

    public PublicationOutput postLike(Long publicationId, Long userId, String token)  {
        

        if (!publicationRepository.existsById(publicationId)) throw new PublicationNotFound(publicationId);
		
        Publication p = publicationRepository.getOne(publicationId);
        User u = userRepository.getOne(userId);

        if (u.getToken() == null) throw new LoginException();
        if (!u.getToken().equals(token)) throw new IncorrectTokenException();

        if (p.getLikedBy().contains(u)) throw new DuplicateActionException("This user has already liked this publication");

        if (p.getDislikedBy().contains(u))  {
            p.getDislikedBy().remove(u);
            u.getDislikedPublications().remove(p); 
        }

        p.addLikedUser(u);
        p = publicationRepository.save(p);
        u.addLikedPublication(p);
        u =userRepository.save(u);

        return new PublicationOutput(p);


    }
    
    public PublicationOutput deleteLike(Long publicationId, Long userId, String token)  {

        Publication p = publicationRepository.getOne(publicationId);
        User u = userRepository.getOne(userId);

        if (u.getToken() == null) throw new LoginException();
        if (!u.getToken().equals(token)) throw new IncorrectTokenException();
        if (!p.getLikedBy().contains(u)) throw new ActionNotPermited ("This user has not liked this publication");

        p.getLikedBy().remove(u);
        u.getLikedPublications().remove(p);
        u = userRepository.save(u);
        p = publicationRepository.save(p);
        return new PublicationOutput(p);
        
    }    
    
    public List<UserOutput> getDislikedUsers(Long publicationId)  {
       Publication p = publicationRepository.getOne(publicationId);
       List<UserOutput> result = new ArrayList<>();
       for (User u: p.getDislikedBy()) {
           result.add(new UserOutput(u));
       }
       return result;
    }



	public PublicationOutput postDislike(Long publicationId, Long userId, String token)  {
		
        Publication p = publicationRepository.getOne(publicationId);
        User u = userRepository.getOne(userId);

        if (u.getToken() == null) throw new LoginException();
        if (!u.getToken().equals(token)) throw new IncorrectTokenException();
        if (p.getDislikedBy().contains(u)) throw new DuplicateActionException("This user has already disliked this publication");

        if (p.getLikedBy().contains(u))  {
            p.getLikedBy().remove(u);
            u.getLikedPublications().remove(p); 
        }

        p.addDislikedUser(u);
        p = publicationRepository.save(p);
        u.addDislikedPublication(p);
        userRepository.save(u);

        return new PublicationOutput(p);	}



	public PublicationOutput deleteDislike(Long publicationId, Long userId, String token)  {
		
        Publication p = publicationRepository.getOne(publicationId);
        User u = userRepository.getOne(userId);

        if (u.getToken() == null) throw new LoginException();
        if (!u.getToken().equals(token)) throw new IncorrectTokenException();
        if (!p.getDislikedBy().contains(u)) throw new ActionNotPermited ("This user has not disliked this publication");

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



    public UserOutput postFavUser(Long id, Long userId, String token)  {
		
        Publication p = publicationRepository.getOne(id);
        User favUser = userRepository.getOne(userId);

        if (favUser.getToken() == null) throw new LoginException();
        if (!favUser.getToken().equals(token)) throw new IncorrectTokenException();
        if (p.getFavouriteBy().contains(favUser)) throw new DuplicateActionException("This user has already faved this publication");
        
        p.addFavUser(favUser);
        favUser.addFavPublication(p);

        p = publicationRepository.save(p);
        favUser = userRepository.save(favUser);

        return new UserOutput(favUser);

    }

    public UserOutput deleteFavUser(Long id, Long userId, String token)  {

		
        Publication p = publicationRepository.getOne(id);
        User favUser = userRepository.getOne(userId);

        if (favUser.getToken() == null) throw new LoginException();
        if (!favUser.getToken().equals(token)) throw new IncorrectTokenException();
        if (!p.getFavouriteBy().contains(favUser)) throw new ActionNotPermited("This user has not faved this publication");
        
        p.removeFavUser(favUser);
        favUser.removeFavPublication(p);

        p = publicationRepository.save(p);
        favUser = userRepository.save(favUser);

        return new UserOutput(favUser);    }

    public List<CommentOutput> getComments(Long publicationId)  {
        Publication p = publicationRepository.getOne(publicationId);
        List<CommentOutput> result = new ArrayList<>();
        for (Comment c: p.getComments()) {
            result.add(new CommentOutput(c));
        }

        return result;
    }



    public List<UserOutput> getMentions(Long publicationId)  {
        Publication publication = publicationRepository.getOne(publicationId);
        List<UserOutput> result = new ArrayList<>();
        for(User mention: publication.getMentions()) {
            result.add(new UserOutput(mention));
        }

        return result;
    }



    public List<TagOutput> getTags(Long publicationId)  {
        Publication p = publicationRepository.getOne(publicationId);
        List<TagOutput> result = new ArrayList<>();
        for (Tag t: p.getTags()) {
            result.add(new TagOutput(t));
        }

        return result;       

    }


    




    




   



    



   










    

    

    
}
