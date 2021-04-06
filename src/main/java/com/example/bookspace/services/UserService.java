package com.example.bookspace.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.models.Publication;
import com.example.bookspace.models.Tag;
import com.example.bookspace.models.User;
import com.example.bookspace.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserOutput> getUsers(){

		List<UserOutput> result = new ArrayList<>();
		
		for (User u: userRepository.findAll()) {
			UserOutput uo = new UserOutput(u);
			result.add(uo);
		}
		return result;
	}

    public UserOutput getUser(Long id) {
		boolean exists = userRepository.existsById(id);
		if(!exists) throw new IllegalStateException("The user with id " + id + " does not exist");
		User u = userRepository.getOne(id);
		return new UserOutput(u);
    }

    public UserOutput addNewUser(UserInput userDetails) {
		Optional<User> userByEmail = userRepository
		.findUserByEmail(userDetails.getEmail());
		if(userByEmail.isPresent()){
			throw new IllegalStateException("email taken");
		}
		User user = new User(userDetails);
		user = userRepository.save(user);
		return new UserOutput(user);
    }

	public void deleteUser(Long userId){
		boolean b = userRepository.existsById(userId);
		if(!b) {
			throw new IllegalStateException("User with id " + userId + " does not exists");
		}
		userRepository.deleteById(userId);

	}

	@Transactional
	public void updateUser(Long id, String name, String description, String email, String username, LocalDate dob, byte[] profile_pic) {
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
		
		if (dob != null && !Objects.equals(user.getDob(), dob)){
				user.setDob(dob);
			}

		if (profile_pic != null && !Objects.equals(user.getProfile_pic(), profile_pic)){
			user.setProfile_pic(profile_pic);
		}
		userRepository.save(user);
	}

    public List<PublicationOutput> getPublicationsUser(Long id) {
		User author = userRepository.getOne(id);
		List<Publication> publications = author.getPublications();
		List<PublicationOutput> result = new ArrayList<>();
		for (Publication p: publications) {
			result.add(new PublicationOutput(p));
		}

		return result;
		
    }

	public List<TagOutput> getPreferedTagsUser(Long id){
		User u = userRepository.getOne(id);
		List<Tag> tags = u.getPreferedTags();
		List<TagOutput> result = new ArrayList<>();
		for (Tag t: tags) {
			result.add(new TagOutput(t));
		}
		return result;
	}

    
    
}
