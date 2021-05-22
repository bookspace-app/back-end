package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {

    public UserNotFoundException(Long id) {
        super("The user with id " + id + " does not exists!");
    }

    public UserNotFoundException(String email) {
        super("The user with email [" + email + "] does not exists!");
    }
    
}
