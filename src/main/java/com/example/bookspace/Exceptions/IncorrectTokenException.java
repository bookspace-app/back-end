package com.example.bookspace.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "The token is incorrect")
public class IncorrectTokenException extends RuntimeException{

    public IncorrectTokenException() {}
    
}
