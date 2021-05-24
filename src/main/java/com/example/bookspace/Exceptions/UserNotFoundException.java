package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class UserNotFoundException extends HttpClientErrorException {

    public UserNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "The user with id " + id + " does not exists!");
    }

    public UserNotFoundException(String email) {
        super(HttpStatus.NOT_FOUND, "The user with email [" + email + "] does not exists!");
    }
    
}
