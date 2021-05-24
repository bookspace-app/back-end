package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CommentNotFound extends HttpClientErrorException{

    public CommentNotFound(Long id) {
        super(HttpStatus.NOT_FOUND, "The comment with id: [" + id + "] not exists");
    }
    
}
    

