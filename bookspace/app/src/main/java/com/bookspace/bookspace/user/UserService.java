package com.bookspace.bookspace.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

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

	@Transactional
	public void updateUser(Long id, String name, String description, String email, String username) {
		User user = userRepository.findById(id)
					.orElseThrow(() -> new IllegalStateException(
						"User with id " + id + " does not exist"));
		
		if (description != null && description.length() > 0 &&
			!Objects.equals(user.getDescription(), description)){
				user.setDescription(description);
			}
		
			if (email != null && email.length() > 0 &&
			!Objects.equals(user.getEmail(), email)){
				Optional<User> userOptional = userRepository.findUserByEmail(email);
				if(userOptional.isPresent()){
					throw new IllegalStateException("email already taken");
				}
				user.setEmail(email);
			}

		if (name != null && name.length() > 0 &&
			!Objects.equals(user.getName(), name)){
				user.setName(name);
			}

		if (username != null && username.length() > 0 &&
			!Objects.equals(user.getUsername(), username)){
				Optional<User> userOptional = userRepository.findUserByUsername(username);
				if(userOptional.isPresent()){
					throw new IllegalStateException("username already taken");
				}
				user.setUsername(username);
			}
		userRepository.save(user);
	}
    
}
