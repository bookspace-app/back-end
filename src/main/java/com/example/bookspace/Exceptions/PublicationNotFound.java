package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PublicationNotFound extends HttpClientErrorException{

    public PublicationNotFound(Long id) {
        super(HttpStatus.NOT_FOUND, "The publication with id: [" + id + "] not exists");
    }
    
}
