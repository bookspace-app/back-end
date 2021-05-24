package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PublicationNotFound extends RuntimeException{

    public PublicationNotFound(Long id) {
        super("The publication with id: [" + id + "] not exists");
    }
    
}
