package com.example.bookspace.services;

import com.example.bookspace.repositories.CommentRepository;
import com.example.bookspace.repositories.PublicationRepository;
import com.example.bookspace.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

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

    public CommentOutput postComment(CommentInput commentDetails) throws Exception {
        if (commentDetails.getAuthorId() == null) throw new Exception("The userId can't be null");
        if (commentDetails.getPublicationId() == null) throw new Exception("The publication can't be null");
        User author = userRepository.getOne(commentDetails.getAuthorId());
        Publication publication = publicationRepository.getOne(commentDetails.getPublicationId());
        
        //Create the comment
        Comment newComment = new Comment(commentDetails.getContent(), author, publication);

        //If there are mentioned users, assign to them 
        if (commentDetails.getMentions() != null) {
            for (Long mentionedId: commentDetails.getMentions()) {
                User mentioned = userRepository.getOne(mentionedId);
                mentioned.getCommentMentions().add(newComment);
                newComment.getCommentMentions().add(mentioned);
                mentioned = userRepository.save(mentioned);
                newComment = commentRepository.save(newComment);
            }
        }

        //If is an aswer, take the parent
        if (commentDetails.getParentId() != null)  {
            Comment parent = commentRepository.getOne(commentDetails.getParentId());
            newComment.setParent(parent);
            parent.getReplies().add(newComment);   
            
            parent = commentRepository.save(parent);
            newComment = commentRepository.save(newComment);
        } 


        
        newComment = commentRepository.save(newComment);



        return new CommentOutput(newComment);
        
    }


    public CommentOutput getComment(Long commentId) {
        Comment c = commentRepository.getOne(commentId);
        return new CommentOutput(c);
    }

    public CommentOutput putComment(Long commentId, CommentInput commentDetails) throws Exception {

        Comment comment = commentRepository.getOne(commentId);


        if (commentDetails.getAuthorId() != null && commentDetails.getAuthorId() != comment.getAuthor().getId()) new Exception("Can't change the comment's author");
        if (commentDetails.getPublicationId() != null && commentDetails.getPublicationId() != comment.getPublication().getId()) throw new Exception("Can't change the comment's publication");
        if (commentDetails.getParentId() != null && comment.getParent() == null) throw new Exception("Can't change a parent comment to a reply");
        if (commentDetails.getParentId() != null && commentDetails.getParentId() != comment.getParent().getId()) throw new Exception("Can't change the parent of a comment");

        if (commentDetails.getContent() != null) {
            comment.setContent(commentDetails.getContent());
            comment = commentRepository.save(comment);
        }
        return new CommentOutput(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.getOne(commentId); 
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

    public CommentOutput postLikeComment(Long commentId, Long userId) {
        Comment comment = commentRepository.getOne(commentId);
        User like = userRepository.getOne(userId);
        
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

    public CommentOutput deleteLikeComment(Long commentId, Long userId) {
        Comment comment = commentRepository.getOne(commentId);
        User like = userRepository.getOne(userId);

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

    public CommentOutput postDislikeComment(Long commentId, Long userId) {
        Comment comment = commentRepository.getOne(commentId);
        User like = userRepository.getOne(userId);
        
        if (comment.getLikedBy().contains(like)) {
            comment.getLikedBy().remove(like);
            like.getLikedComments().remove(comment);
        }

        if (!comment.getDislikedBy().contains(like)) {
            comment.getDislikedBy().add(like);
            like.getDislikedComments().add(comment);
        }

        comment = commentRepository.save(comment);
        like = userRepository.save(like);

        return new CommentOutput(comment);
    }

    public CommentOutput deleteDislikeComment(Long commentId, Long userId) {
        Comment comment = commentRepository.getOne(commentId);
        User like = userRepository.getOne(userId);

        if (comment.getDislikedBy().contains(like)) {
            comment.getDislikedBy().remove(like);
            like.getDislikedComments().remove(comment);
        }

        comment = commentRepository.save(comment);
        like = userRepository.save(like);

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
