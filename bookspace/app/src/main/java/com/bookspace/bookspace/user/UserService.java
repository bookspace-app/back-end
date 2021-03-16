package com.bookspace.bookspace.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

    public List<User> getStudents() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
		Optional<User> userByEmail = userRepository
		.findUserByEmail(user.getEmail());
		if(userByEmail.isPresent()){
			throw new IllegalStateException("email taken");
		}
		userRepository.save(user);
    }
    
}
