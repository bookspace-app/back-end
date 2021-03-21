package com.bookspace.bookspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BookspaceApplication {

	@RequestMapping("/")
	@ResponseBody
	public static void main(String[] args) {
		SpringApplication.run(BookspaceApplication.class, args);
	}

}
