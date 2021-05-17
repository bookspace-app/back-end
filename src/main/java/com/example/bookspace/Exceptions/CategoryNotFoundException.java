package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException{
    
    public CategoryNotFoundException(String category) {
        super("The category: [" + category + "] not exists");
    }
}
