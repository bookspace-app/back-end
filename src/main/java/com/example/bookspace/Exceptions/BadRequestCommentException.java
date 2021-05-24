package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;


public class BadRequestCommentException extends HttpClientErrorException {
    public BadRequestCommentException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    
}
