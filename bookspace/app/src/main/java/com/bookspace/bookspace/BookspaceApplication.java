package com.bookspace.bookspace;

import java.time.LocalDate;
import java.util.List;

import com.bookspace.bookspace.user.User;
import com.bookspace.bookspace.enums.Rank;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BookspaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookspaceApplication.class, args);
	}

	@GetMapping
	public List<User> hello() {
		return List.of(
			new User(
			"email",
			"name",
			LocalDate.now(),
			Rank.QUEEN,
			LocalDate.now())
		);
	}

}
