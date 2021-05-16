package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CommentNotFound extends RuntimeException{

    public CommentNotFound(Long id) {
        super("The comment with id: [" + id + "] not exists");
    }
    
}
    

