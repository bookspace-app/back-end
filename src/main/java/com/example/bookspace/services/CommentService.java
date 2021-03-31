package com.example.bookspace.services;

import com.example.bookspace.repositories.CommentRepository;
import java.util.List;
import java.util.Optional;

import com.example.bookspace.models.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getComment(Long commentId) {
		boolean exists = commentRepository.existsById(commentId);
		if(!exists) throw new IllegalStateException("The comment with id " + commentId + " does not exist");
        return commentRepository.findById(commentId);
    }
}
