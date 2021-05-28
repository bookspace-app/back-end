package com.example.bookspace.services;

import com.example.bookspace.repositories.CommentRepository;
import com.example.bookspace.repositories.PublicationRepository;
import com.example.bookspace.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import com.example.bookspace.Exceptions.BadRequestCommentException;
import com.example.bookspace.Exceptions.CommentNotFound;
import com.example.bookspace.Exceptions.IncorrectTokenException;
import com.example.bookspace.Exceptions.LoginException;
import com.example.bookspace.Exceptions.PublicationNotFound;
import com.example.bookspace.Exceptions.UserNotFoundException;
import com.example.bookspace.Inputs.CommentInput;
import com.example.bookspace.Output.CommentOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.models.Comment;
import com.example.bookspace.models.Publication;
import com.example.bookspace.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;


    @Autowired
	public CommentService(CommentRepository commentRepository, UserRepository userRepository, PublicationRepository publicationRepository) {
		this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.publicationRepository = publicationRepository;
	}

    public List<CommentOutput> getComments() {
        List<CommentOutput> result = new ArrayList<>();
        for (Comment c: commentRepository.findAll()) {
            result.add(new CommentOutput(c));
        }
        return result;
    }

    public CommentOutput postComment(CommentInput commentDetails, String token) throws LoginException, IncorrectTokenException, PublicationNotFound, CommentNotFound {

        if (!userRepository.existsById(commentDetails.getAuthorId())) throw new UserNotFoundException(commentDetails.getAuthorId());

        User author = userRepository.getOne(commentDetails.getAuthorId());

        if (author.getToken() == null) throw new LoginException();
        if (!author.getToken().equals(token)) throw new IncorrectTokenException();

		if (!publicationRepository.existsById(commentDetails.getPublicationId())) throw new PublicationNotFound(commentDetails.getPublicationId());
		
        Publication publication = publicationRepository.getOne(commentDetails.getPublicationId());

        
        //Create the comment
        Comment newComment = new Comment(commentDetails.getContent(), author, publication);

        //If there are mentioned users, assign to them 
        if (commentDetails.getMentions() != null) {
            for (String username: commentDetails.getMentions()) {
                if (userRepository.findUserByUsername(username).isPresent()) {
                    User mentioned = userRepository.getUserByUsername(username);
                    mentioned.getCommentMentions().add(newComment);
                    newComment.getCommentMentions().add(mentioned);
                    mentioned = userRepository.save(mentioned);
                }
            }
        }

        //If is an aswer, get  parent and associate. 

        if (commentDetails.getParentId() != null && commentDetails.getParentId() != 0)  {
            if (!commentRepository.existsById(commentDetails.getParentId())) throw new CommentNotFound(commentDetails.getParentId());

            Comment parent = commentRepository.getOne(commentDetails.getParentId());
            newComment.setParent(parent);
            parent.getReplies().add(newComment);   
            newComment = commentRepository.save(newComment);
            parent = commentRepository.save(parent);
            
        } 
        else  {
            /*If is a direct comment, update in publication */
            publication.addDirectComment();
            publication = publicationRepository.save(publication);
            newComment = commentRepository.save(newComment);

        }

        return new CommentOutput(newComment);
        
    }


    public CommentOutput getComment(Long commentId) {
        Comment c = commentRepository.getOne(commentId);
        return new CommentOutput(c);
    }

    public CommentOutput putComment(Long commentId, CommentInput commentDetails, String token) {
        
        User author = userRepository.getOne(commentDetails.getAuthorId());

        if (author.getToken() == null) throw new LoginException();
        if (!author.getToken().equals(token)) throw new IncorrectTokenException();

		    if (!commentRepository.existsById(commentId)) throw new CommentNotFound(commentId);
		
        Comment comment = commentRepository.getOne(commentId);


        if (commentDetails.getAuthorId() != null && commentDetails.getAuthorId() != comment.getAuthor().getId()) new BadRequestCommentException("Can't change the comment's author");
        if (commentDetails.getPublicationId() != null && commentDetails.getPublicationId() != comment.getPublication().getId()) throw new BadRequestCommentException("Can't change the comment's publication");
        if (commentDetails.getParentId() != null && comment.getParent() == null) throw new BadRequestCommentException("Can't change a parent comment to a reply");
        if (commentDetails.getParentId() != null && commentDetails.getParentId() != comment.getParent().getId()) throw new BadRequestCommentException("Can't change the parent of a comment");

        if (commentDetails.getContent() != null) {
            comment.setContent(commentDetails.getContent());
            comment = commentRepository.save(comment);
        }
        return new CommentOutput(comment);
    }

    public void deleteComment(Long commentId, String token) throws LoginException {

        if (!commentRepository.existsById(commentId)) throw new CommentNotFound(commentId);
		
        Comment comment = commentRepository.getOne(commentId);   

        User author = userRepository.getOne(comment.getAuthor().getId());

        if (author.getToken() == null) throw new LoginException();
        if (!author.getToken().equals(token)) throw new IncorrectTokenException();

        /*If is a direct comment, remove one from the publication attribute */
        if (comment.getParent() == null) {
            Publication publication = publicationRepository.getOne(comment.getPublication().getId());
            publication.removeDirectComment();
            publication = publicationRepository.save(publication);
        }

    		author.getComments().remove(comment);
        author = userRepository.save(author);

        commentRepository.delete(comment);
    }

    public List<CommentOutput> getCommentReplies(Long commentId) {
        Comment comment = commentRepository.getOne(commentId);
        List<CommentOutput> result = new ArrayList<>();
        for (Comment reply: comment.getReplies()) {
            result.add(new CommentOutput(reply));
        }

        return result;
    }

    public List<UserOutput> getLikedUsers(Long commentId) {
        Comment comment = commentRepository.getOne(commentId);
        List<UserOutput> result = new ArrayList<>();
        for (User likedUser: comment.getLikedBy()) {
            result.add(new UserOutput(likedUser));
        }

        return result;
    }

    public CommentOutput postLikeComment(Long commentId, Long userId, String token) throws LoginException {
        User like = userRepository.getOne(userId);

        if (like.getToken() == null) throw new LoginException();
        if (!like.getToken().equals(token)) throw new IncorrectTokenException();

		if (!commentRepository.existsById(commentId)) throw new CommentNotFound(commentId);
		
        Comment comment = commentRepository.getOne(commentId);
        
        if (comment.getDislikedBy().contains(like)) {
            comment.getDislikedBy().remove(like);
            like.getDislikedComments().remove(comment);
        }

        if (!comment.getLikedBy().contains(like)) {
            comment.getLikedBy().add(like);
            like.getLikedComments().add(comment);
        }

        comment = commentRepository.save(comment);
        like = userRepository.save(like);

        return new CommentOutput(comment);
    }

    public CommentOutput deleteLikeComment(Long commentId, Long userId, String token) throws LoginException {
        User like = userRepository.getOne(userId);

        if (like.getToken() == null) throw new LoginException();
        if (!like.getToken().equals(token)) throw new IncorrectTokenException();

		if (!commentRepository.existsById(commentId)) throw new CommentNotFound(commentId);
		
        Comment comment = commentRepository.getOne(commentId);

        if (comment.getLikedBy().contains(like)) {
            comment.getLikedBy().remove(like);
            like.getLikedComments().remove(comment);
        }

        comment = commentRepository.save(comment);
        like = userRepository.save(like);

        return new CommentOutput(comment);


    }

    public List<UserOutput> getDislikedUsers(Long commentId, Long userId) {
        Comment comment = commentRepository.getOne(commentId);
        List<UserOutput> result = new ArrayList<>();
        for (User dislikedUser: comment.getDislikedBy()) {
            result.add(new UserOutput(dislikedUser));
        }

        return result;
    }

    public CommentOutput postDislikeComment(Long commentId, Long userId, String token) throws LoginException {
        User dislike = userRepository.getOne(userId);

        if (dislike.getToken() == null) throw new LoginException();
        if (!dislike.getToken().equals(token)) throw new IncorrectTokenException();

		if (!commentRepository.existsById(commentId)) throw new CommentNotFound(commentId);
		
        Comment comment = commentRepository.getOne(commentId);
        
        if (comment.getLikedBy().contains(dislike)) {
            comment.getLikedBy().remove(dislike);
            dislike.getLikedComments().remove(comment);
        }

        if (!comment.getDislikedBy().contains(dislike)) {
            comment.getDislikedBy().add(dislike);
            dislike.getDislikedComments().add(comment);
        }

        comment = commentRepository.save(comment);
        dislike = userRepository.save(dislike);

        return new CommentOutput(comment);
    }

    public CommentOutput deleteDislikeComment(Long commentId, Long userId, String token) {
        User dislike = userRepository.getOne(userId);

        if (dislike.getToken() == null) throw new LoginException();
        if (!dislike.getToken().equals(token)) throw new IncorrectTokenException();

		if (!commentRepository.existsById(commentId)) throw new CommentNotFound(commentId);
		
        Comment comment = commentRepository.getOne(commentId);

        if (comment.getDislikedBy().contains(dislike)) {
            comment.getDislikedBy().remove(dislike);
            dislike.getDislikedComments().remove(comment);
        }

        comment = commentRepository.save(comment);
        dislike = userRepository.save(dislike);

        return new CommentOutput(comment);
    }

    public List<UserOutput> getCommentMentions(Long commentId) {

        Comment comment = commentRepository.getOne(commentId);
        List<UserOutput> result = new ArrayList<>();
        for (User mentionedUser: comment.getCommentMentions()) {
            result.add(new UserOutput(mentionedUser));
        }

        return result;
    }

   
    

   
  
   

    
}
