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

	public List<User> getUsers(){
		return userRepository.findAll();
	}

    public Optional<User> getUser(Long id) {
		boolean exists = userRepository.existsById(id);
		if(!exists) throw new IllegalStateException("The user with id " + id + " does not exist");
        return userRepository.findById(id);
    }

    public void addNewUser(User user) {
		Optional<User> userByEmail = userRepository
		.findUserByEmail(user.getEmail());
		if(userByEmail.isPresent()){
			throw new IllegalStateException("email taken");
		}
		userRepository.save(user);
    }

	public void deleteUser(Long userId){
		boolean b = userRepository.existsById(userId);
		if(!b) {
			throw new IllegalStateException("User with id " + userId + " does not exists");
		}
		userRepository.deleteById(userId);

	}
    
}
