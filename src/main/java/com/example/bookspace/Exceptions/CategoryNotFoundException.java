package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends HttpClientErrorException{
    
    public CategoryNotFoundException(String category) {
        super(HttpStatus.NOT_FOUND, "The category: [" + category + "] not exists");
    }
}
