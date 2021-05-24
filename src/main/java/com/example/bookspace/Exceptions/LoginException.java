package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class LoginException extends HttpClientErrorException{
    public LoginException() {
        super(HttpStatus.NOT_FOUND, "The user is not logged in");
    }
    
}
