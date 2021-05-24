package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ActionNotPermited extends HttpClientErrorException{
    public ActionNotPermited(String message) {
        super(HttpStatus.NOT_ACCEPTABLE, message);

    }

    
}
