package com.example.bookspace.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.example.bookspace.Inputs.CommentInput;
import com.example.bookspace.Inputs.PublicationInput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.enums.Category;
import com.example.bookspace.Output.CommentOutput;
import com.example.bookspace.models.Comment;
import com.example.bookspace.models.Publication;
import com.example.bookspace.models.User;
import com.example.bookspace.repositories.CommentRepository;
import com.example.bookspace.repositories.PublicationRepository;
import com.example.bookspace.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

     

    public List<PublicationOutput> getPublications() {
        List<PublicationOutput> result = new ArrayList<>();

        for (Publication p: publicationRepository.findAll()) {
            PublicationOutput po = new PublicationOutput(p);
            result.add(po);
        }

        return result;
    }

    public PublicationOutput getPublication(Long id) {
        Publication p = publicationRepository.getOne(id);
        p.addView();
        publicationRepository.save(p);
        return new PublicationOutput(p);
    }


    public PublicationOutput postPublication(PublicationInput publicationDetails) {
        User author = userRepository.findById(publicationDetails.getAuthor()).get();
        Publication publication = new Publication(publicationDetails.getTitle(), publicationDetails.getContent(), author, publicationDetails.getCategory());
        publication = publicationRepository.save(publication);
        author.addPublication(publication);
        userRepository.save(author);
        return new PublicationOutput(publication);

        
    
    }

    @Transactional
	public void updatePublication(Long id, PublicationInput publicationDetails) throws Exception {
		Publication publication = publicationRepository.findById(id)
					.orElseThrow(() -> new IllegalStateException(
						"Publication with id " + id + " does not exist"));


        if (publication.getAuthor().getId() != publicationDetails.getAuthor()) throw new Exception ("This publication is owned by another author");
        
        publication.setTitle(publicationDetails.getTitle());
        publication.setContent(publicationDetails.getContent());
        publication.setCategory(Category.ACTION);

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



    public PublicationOutput postLike(Long id) {
        Publication p = publicationRepository.getOne(id);
        
        p.addLike();

        publicationRepository.save(p);
        return new PublicationOutput(p);
    }



    public PublicationOutput postDislike(Long id) {
        Publication p = publicationRepository.getOne(id);
        
        p.removeLike();

        publicationRepository.save(p);
        
        return new PublicationOutput(p);
    }



    public List<CommentOutput> getComments(Long id) {
        Publication p = publicationRepository.getOne(id);
        List<CommentOutput> result = new ArrayList<>();
        for (Comment c: p.getComments())  {
            result.add(new CommentOutput(c));
        }

        return result;
    }



    public CommentOutput postComment(Long id, CommentInput commentDetails) {
        User author = userRepository.getOne(commentDetails.getAuthor());
        Publication publication = publicationRepository.getOne(id);
        Comment comment = new Comment(commentDetails.getContent(), author, publication);
        comment = commentRepository.save(comment);

        //Si no es comentario padre
        if (commentDetails.getParent() != 0){
            Comment parent = commentRepository.getOne(commentDetails.getParent()); 
            comment.setParent(parent);
            comment = commentRepository.save(comment);
        }        
        
        return new CommentOutput(comment);
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
