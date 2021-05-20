package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestCommentException extends RuntimeException {
    public BadRequestCommentException(String message) {
        super(message);
    }

    
}
