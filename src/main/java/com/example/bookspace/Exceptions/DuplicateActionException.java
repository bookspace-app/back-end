package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class DuplicateActionException extends HttpClientErrorException {
    public DuplicateActionException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
    
}
