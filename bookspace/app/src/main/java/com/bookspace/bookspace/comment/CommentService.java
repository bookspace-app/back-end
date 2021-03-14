package com.bookspace.bookspace.comment;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CommentService {
    public List<String> getComments() {
        return List.of("Hello", "comments");
    }
}
