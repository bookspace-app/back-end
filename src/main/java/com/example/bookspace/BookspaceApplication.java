package com.example.bookspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BookspaceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(BookspaceApplication.class, args);
	}

}

@RestController
class HelloController {
	@GetMapping
	String hello() {
		return "Principal Bookspace-app api page";
	}
}