package com.example.bookspace.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.enums.Category;
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

    public UserOutput postUser(UserInput userDetails) throws Exception {
		
		if (userDetails.getEmail() == null) throw new Exception("The email can¡' be empty");
		if (userRepository.findUserByEmail(userDetails.getEmail()).isPresent()) throw new Exception("This email is already used");
		if (userDetails.getName() == null) throw new Exception("The name can't be empty");
		if (userDetails.getUsername() == null) throw new Exception("The username can't be empty");
		if (userRepository.findUserByUsername(userDetails.getUsername()).isPresent()) throw new Exception("This username is already used");
		if (userDetails.getPassword() == null) throw new Exception("The password can't be empty");
		if (userDetails.getDob() == null) throw new Exception("The date of birth can't be empty");
		
		User user = new User(userDetails.getEmail(), userDetails.getName(), userDetails.getUsername(), userDetails.getPassword(), userDetails.getDob());
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
	public UserOutput updateUser(Long id, UserInput userDetails) {
		User user = userRepository.findById(id)
					.orElseThrow(() -> new IllegalStateException(
						"User with id " + id + " does not exist"));
		
		if (userDetails.getDescription() != null && userDetails.getDescription().length() > 0 &&
			!Objects.equals(user.getDescription(), userDetails.getDescription())){
				user.setDescription(userDetails.getDescription());
			}
		
		if (userDetails.getEmail() != null && userDetails.getEmail().length() > 0 &&
			!Objects.equals(user.getEmail(), userDetails.getEmail())){
				Optional<User> userOptional = userRepository.findUserByEmail(userDetails.getEmail());
				if(userOptional.isPresent()){
					throw new IllegalStateException("email already taken");
				}
				user.setEmail(userDetails.getEmail());
			}

		if (userDetails.getName() != null && userDetails.getName().length() > 0 &&
			!Objects.equals(user.getName(), userDetails.getName())){
				user.setName(userDetails.getName());
			}

		if (userDetails.getUsername() != null && userDetails.getUsername() .length() > 0 &&
			!Objects.equals(user.getUsername(), userDetails.getUsername() )){
				Optional<User> userOptional = userRepository.findUserByUsername(userDetails.getUsername() );
				if(userOptional.isPresent()){
					throw new IllegalStateException("username already taken");
				}
				user.setUsername(userDetails.getUsername() );
			}
		
		if (userDetails.getDob()  != null && !Objects.equals(user.getDob(), userDetails.getDob() )){
				user.setDob(userDetails.getDob() );
			}
		
		if (userDetails.getFavCategories() != null){
			List<Category> cateogories = Category.getCategories(userDetails.getFavCategories());
			user.setFavCategories(cateogories);
		}
		user = userRepository.save(user);
		return new UserOutput(user);
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

	public List<TagOutput> getFavTagsUser(Long id){
		User u = userRepository.getOne(id);
		List<Tag> tags = u.getfavTags();
		List<TagOutput> result = new ArrayList<>();
		for (Tag t: tags) {
			result.add(new TagOutput(t));
		}
		return result;
	}

	public List<String> getFavCategoriesUser(Long id) {
		User user = userRepository.getOne(id);
		return Category.parseToString(user.getFavCategories());
	}

    
    
}
