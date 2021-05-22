package com.example.bookspace.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "The user is already logged in")

public class AlreadyLoginException extends RuntimeException {
    public AlreadyLoginException() {}
}
