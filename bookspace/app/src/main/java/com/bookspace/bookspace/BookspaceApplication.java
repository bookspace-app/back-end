package com.bookspace.bookspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BookspaceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(BookspaceApplication.class, args);
	}
}

@RestController
class HelloController {
	@GetMapping("/")
	String hello() {
		return "hello world";
	}
}
