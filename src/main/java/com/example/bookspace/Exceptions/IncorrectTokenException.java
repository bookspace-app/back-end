package com.example.bookspace.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class IncorrectTokenException extends HttpClientErrorException{

    public IncorrectTokenException() {
        super(HttpStatus.UNAUTHORIZED, "The token is incorrect");
    }
    
}
