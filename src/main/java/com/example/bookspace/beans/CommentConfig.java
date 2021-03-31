package com.example.bookspace.beans;

import java.util.List;

import com.example.bookspace.models.Comment;
import com.example.bookspace.repositories.CommentRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

public class CommentConfig {
    @Bean
    CommandLineRunner commandLineRunnerComment(CommentRepository repository) {
        return args ->  {
            Comment demo = new Comment();
            repository.saveAll(List.of(demo));
        };
    }
}
