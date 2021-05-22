package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ActionNotPermited extends RuntimeException{
    public ActionNotPermited(String message) {
        super(message);
    }

    
}
