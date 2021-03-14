package com.bookspace.bookspace.user;

import java.time.LocalDate;
import java.util.List;

import com.bookspace.bookspace.enums.Rank;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    public List<User> getStudents() {
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
