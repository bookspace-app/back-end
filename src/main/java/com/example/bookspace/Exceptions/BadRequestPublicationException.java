package com.example.bookspace.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;


public class BadRequestPublicationException extends HttpClientErrorException{

    public BadRequestPublicationException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }   
    
}
