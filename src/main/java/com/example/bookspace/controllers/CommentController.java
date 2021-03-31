package com.example.bookspace.controllers;

import java.util.List;
import java.util.Optional;

import com.example.bookspace.models.Comment;
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
	public List<Comment> getComment() {
       return commentService.getComments();
	}

    @GetMapping(path = "{commentId}")   
	public Optional<Comment> getCommentById(@PathVariable("commentId") Long commentId) {
        return commentService.getComment(commentId);
    }

    @DeleteMapping(path = "{commentId}")
	public Boolean deleteComment(@PathVariable("commentId") Long commentId) {
        return commentService.deleteComment(commentId);
	}
}
