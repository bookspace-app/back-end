package com.bookspace.bookspace;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/hello")
    public String index() {
        return "Hello there! I'm running.";
    }
}
