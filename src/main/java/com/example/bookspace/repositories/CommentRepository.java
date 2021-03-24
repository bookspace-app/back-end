package com.example.bookspace.repositories;

import com.example.bookspace.models.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
