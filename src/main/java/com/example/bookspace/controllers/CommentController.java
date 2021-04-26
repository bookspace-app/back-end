package com.example.bookspace.controllers;

import java.util.List;

import com.example.bookspace.Inputs.CommentInput;
import com.example.bookspace.Output.CommentOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/comments")

public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping    
	public List<CommentOutput> getComment() {
       return commentService.getComments();
	}

    @PostMapping    
	public CommentOutput postComment(@RequestBody CommentInput commentDetails) throws Exception {
       return commentService.postComment(commentDetails);
	}

    @GetMapping(path = "{commentId}")   
	public CommentOutput getCommentById(@PathVariable("commentId") Long commentId) {
        return commentService.getComment(commentId);
    }

    @PutMapping (path = "{commentId}")  
	public CommentOutput putComment(@PathVariable("commentId") Long commentId, @RequestBody CommentInput commentDetails) throws Exception {
       return commentService.putComment(commentId, commentDetails);
	}

    @DeleteMapping(path = "{commentId}")
	public void deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
	}

    @GetMapping(path = "{commentId}/replies")   
	public List<CommentOutput> getCommentReplies(@PathVariable("commentId") Long commentId) {
        return commentService.getCommentReplies(commentId);
    }

    @GetMapping(path = "{commentId}/mentions")   
	public List<UserOutput> getCommentMentions(@PathVariable("commentId") Long commentId) {
        return commentService.getCommentMentions(commentId);
    }

    @GetMapping(path = "{commentId}/like")   
	public List<UserOutput> getLikedUsers(@PathVariable("commentId") Long commentId) {
        return commentService.getLikedUsers(commentId);
    }

    @PostMapping(path = "{commentId}/like/{userId}")   
	public CommentOutput postLikeUser(@PathVariable("commentId") Long commentId, @PathVariable("userId") Long userId) {
        return commentService.postLikeComment(commentId, userId);
    }

    @DeleteMapping(path = "{commentId}/like/{userId}")   
	public CommentOutput deleteLikeUser(@PathVariable("commentId") Long commentId, @PathVariable("userId") Long userId) {
        return commentService.deleteLikeComment(commentId, userId);
    }

    @GetMapping(path = "{commentId}/dislike")   
	public List<UserOutput> getDislikedUsers(@PathVariable("commentId") Long commentId, @PathVariable("userId") Long userId) {
        return commentService.getDislikedUsers(commentId, userId);
    }

    @PostMapping(path = "{commentId}/dislike/{userId}")   
	public CommentOutput postDislikeUser(@PathVariable("commentId") Long commentId, @PathVariable("userId") Long userId) {
        return commentService.postDislikeComment(commentId, userId);
    }

    @DeleteMapping(path = "{commentId}/dislike/{userId}")   
	public CommentOutput deleteDislikeComment(@PathVariable("commentId") Long commentId, @PathVariable("userId") Long userId) {
        return commentService.deleteDislikeComment(commentId, userId);
    }



}
