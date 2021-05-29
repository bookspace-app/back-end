package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class TagNotFoundException extends HttpClientErrorException{
    public TagNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
     
}
