package com.example.bookspace.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


import javax.transaction.Transactional;

import com.example.bookspace.Exceptions.AlreadyLoginException;
import com.example.bookspace.Exceptions.IncorrectTokenException;
import com.example.bookspace.Exceptions.LoginException;
import com.example.bookspace.Exceptions.UserNotFoundException;
import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.Output.CommentOutput;
import com.example.bookspace.Output.MentionOutput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.enums.Category;
import com.example.bookspace.models.Comment;
import com.example.bookspace.models.Publication;
import com.example.bookspace.models.Tag;
import com.example.bookspace.models.User;
import com.example.bookspace.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/*
		Gets all users in the system
	*/
	public List<UserOutput> getUsers(){

		List<UserOutput> result = new ArrayList<>();
		
		for (User u: userRepository.findAll()) {
			UserOutput uo = new UserOutput(u);
			result.add(uo);
		}
		return result;
	}

	/* Registers a new user in the system 
		Ensure there is no repetead fields with other users
	*/
	public UserOutput postUser(UserInput userDetails) throws Exception {
	
		if (userDetails.getEmail() == null) throw new HttpMessageConversionException("The email cannot be empty");
		else if (userRepository.findUserByEmail(userDetails.getEmail()).isPresent()) throw new HttpMessageConversionException("This email is already used");
		else if (userDetails.getName() == null) throw new HttpMessageConversionException("The name can't be empty");
		else if (userDetails.getUsername() == null) throw new HttpMessageConversionException("The username can't be empty");
		else if (userRepository.findUserByUsername(userDetails.getUsername()).isPresent()) throw new HttpMessageConversionException("This username is already used");
		else if (userDetails.getPassword() == null) throw new HttpMessageConversionException("The password can't be empty");
		else if (userDetails.getDob() == null)throw new HttpMessageConversionException("The date of birth can't be empty");
		else {
			User user = new User(userDetails.getEmail(), userDetails.getName(), userDetails.getUsername(), userDetails.getPassword(), userDetails.getDob());
			user = userRepository.save(user);
			return new UserOutput(user);
		}
    }

	/*
		Returns the user with id = userId
	*/
    public UserOutput getUser(Long userId) {
		boolean exists = userRepository.existsById(userId);
		if(!exists) throw new IllegalStateException("The user with id " + userId + " does not exist");
		User u = userRepository.getOne(userId);
		return new UserOutput(u);
    }

	/*Modify the fields of the user 
	The token passed in the body has to be the same as the one saved on db */
	
	@Transactional
	public UserOutput putUser(Long id, UserInput userDetails, String token) throws IncorrectTokenException, UserNotFoundException, LoginException {
		User user = new User();

		if (!userRepository.existsById(id)) throw new UserNotFoundException(id);
		user = userRepository.getOne(id);
		if (user.getToken() != null) {
			if (user.getToken().equals(token)) {
				user = userRepository.findById(id)
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
					user.setEmail(userDetails.getEmail());
			
				

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
				}
				else throw new IncorrectTokenException();
			}
			else throw new LoginException();
		user = userRepository.save(user);
		return new UserOutput(user);
	}

	public void deleteUser(Long userId, String token) throws IncorrectTokenException, UserNotFoundException{

		if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
		User user = userRepository.getOne(userId);

		if (user.getToken().equals(token)) userRepository.delete(user);
		else throw new IncorrectTokenException();
	}

	

	public void getProfilePic(Long userId) throws Exception {
		throw new Exception("This endpoint is not implemented yet");
    }

	public void postProfilePic(Long userId) throws Exception {
		throw new Exception("This endpoint is not implemented yet");
	}

    public void deleteProfilePic(Long userId) throws Exception {
		throw new Exception("This endpoint is not implemented yet");

    }    
	
    public List<String> getFavCategoriesUser(Long id) {
		User user = userRepository.getOne(id);
		return Category.parseToString(user.getFavCategories());
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

	public List<PublicationOutput> getLikedPublications(Long id) throws Exception {
		User author = userRepository.getOne(id);
		List<Publication> publications = author.getLikedPublications();
		List<PublicationOutput> result = new ArrayList<>();
		for (Publication p: publications) {
			result.add(new PublicationOutput(p));
		}

		return result;
    }

    public List<PublicationOutput> getDislikedPublications(Long id) throws Exception {
		User author = userRepository.getOne(id);
		List<Publication> publications = author.getDislikedPublications();
		List<PublicationOutput> result = new ArrayList<>();
		for (Publication p: publications) {
			result.add(new PublicationOutput(p));
		}
		return result;
	}
	
	public List<PublicationOutput> getFavPublications(Long id) throws Exception {
		User author = userRepository.getOne(id);
		List<Publication> publications = author.getFavouritePublications();
		List<PublicationOutput> result = new ArrayList<>();
		for (Publication p: publications) {
			result.add(new PublicationOutput(p));
		}
		return result;
	}
	
	public List<MentionOutput> getMentions(Long id) throws Exception {
		User author = userRepository.getOne(id);
		List<Publication> publications = author.getMentions();
		List<Comment> comments = author.getCommentMentions();
		List<MentionOutput> result = new ArrayList<>();
		for (Publication p: publications) {
			result.add(new MentionOutput(p));
		}
		for (Comment c: comments) {
			result.add(new MentionOutput(c));
		}

		return result;
	}

	public List<CommentOutput> getComments(Long id) throws Exception {
		User author = userRepository.getOne(id);
		List<Comment> comments = author.getComments();
		List<CommentOutput> result = new ArrayList<>();
		for (Comment c: comments) {
			result.add(new CommentOutput(c)); //to implment
		}
		return result;
	}

    public List<CommentOutput> getLikedComments(Long id) throws Exception {
		User author = userRepository.getOne(id);
		List<Comment> comments = author.getLikedComments();
		List<CommentOutput> result = new ArrayList<>();
		for (Comment c: comments) {
			result.add(new CommentOutput(c)); //to implment
		}
		return result;    }

	public List<CommentOutput> getDislikedComments(Long id) throws Exception {
		User author = userRepository.getOne(id);
		List<Comment> comments = author.getDislikedComments();
		List<CommentOutput> result = new ArrayList<>();
		for (Comment c: comments) {
			result.add(new CommentOutput(c)); //to implment
		}
		return result;
    }

	public List<TagOutput> getCreatedTags(Long id) throws Exception {
		User author = userRepository.getOne(id);
		List<Tag> tags = author.getCreatedTags();
		List<TagOutput> result = new ArrayList<>();
		for (Tag t: tags) {
			result.add(new TagOutput(t)); //to implment
		}
		return result;
    }

	public List<TagOutput> getFavTagsUser(Long id){
		User u = userRepository.getOne(id);
		List<Tag> tags = u.getFavTags();
		List<TagOutput> result = new ArrayList<>();
		for (Tag t: tags) {
			result.add(new TagOutput(t));
		}
		return result;
	}

    public List<UserOutput> getBlockedUsers(Long id) throws Exception {
		User user = userRepository.getOne(id);
		List<User> users = user.getBlockedUsers();
		List<UserOutput> result = new ArrayList<>();
		for (User u: users) {
			result.add(new UserOutput(u));
		}
		userRepository.save(user);
		return result;
    }

   	public UserOutput postBlockedUsers(Long id, Long blockedUserid, String token) throws Exception {
		
		if (!userRepository.existsById(id)) throw new UserNotFoundException(id);
		User user = userRepository.getOne(id);

		if (!userRepository.existsById(blockedUserid)) throw new UserNotFoundException(blockedUserid);
		User userToBlock = userRepository.getOne(blockedUserid);

		if (user.getToken().equals(token)) {
			if (id == blockedUserid) throw new Exception("You cannot block yourself!");
			
			List<User> blockedUsers = user.getBlockedUsers();
			if (!blockedUsers.contains(userToBlock)){
				blockedUsers.add(userToBlock);
				user.setBlockedUsers(blockedUsers);
				user = userRepository.save(user);
				return new UserOutput(user);
			}
			else throw new Exception("This user is already blocked");
		}
		else throw new IncorrectTokenException();
	}

    public UserOutput deleteBlockedUsers(Long id, Long blockedUserid, String token) throws Exception {
		
		if (!userRepository.existsById(id)) throw new UserNotFoundException(id);
		User user = userRepository.getOne(id);

		if (!userRepository.existsById(blockedUserid)) throw new UserNotFoundException(blockedUserid);
		User userToUnblock = userRepository.getOne(blockedUserid);
		
		if (user.getToken().equals(token)) {

			List<User> blockedUsers = user.getBlockedUsers();
			if (blockedUsers.contains(userToUnblock)){
				blockedUsers.remove(userToUnblock);
				user.setBlockedUsers(blockedUsers);
				user = userRepository.save(user);
				return new UserOutput(user);
			}
			else throw new Exception("This user is not blocked yet"); 
		}
		else throw new HttpMessageConversionException("You are not authorized");

		
    }

	public UserOutput getUserByUsername(String username) throws Exception {
		if (!userRepository.findUserByUsername(username).isPresent()) throw new Exception("It does not exists a user with username " + username);
		User user = userRepository.getUserByUsername(username);
		return new UserOutput(user);
		
	}

	public Map<String, String> loginUser(UserInput userDetails) throws Exception {
		
		if (userDetails.getEmail() == null) throw new HttpMessageConversionException("The mail can't be null");
		if (userDetails.getPassword() == null) new HttpMessageConversionException("The password can't be null");

		String email = userDetails.getEmail();

		if (!userRepository.findUserByEmail(email).isPresent()) throw new UserNotFoundException(email);
		User user = userRepository.getUserByEmail(email);

		if (user.getToken() != null) throw new AlreadyLoginException();
		
	
		if (user.getPassword().equals(userDetails.getPassword())) {

			Map<String, String> result = new HashMap<String, String>(); 
			String token = RandomString.make();
			user.setToken(token);
			user = userRepository.save(user);
			result.put("userId", user.getId().toString());
			result.put("token", token);
			return result;
		}
		else throw new HttpMessageConversionException("The password is incorrect");

			
	}

	
    public void logout(Long userId, String token) throws UserNotFoundException, IncorrectTokenException, LoginException {

		if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
		User user = userRepository.getOne(userId);

		

		if (user.getToken() != null) {
			if (user.getToken().equals(token)) {
				user.setToken(null);
				userRepository.save(user);
			}
			else throw new IncorrectTokenException();
		}
		else throw new LoginException();

		
    }

	public Map<String, String> getToken(Long userId) throws Exception {

        if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);		
	
		else {
			User user = userRepository.getOne(userId);
			if (user.getToken() == null) throw new LoginException();
            Map<String, String> result = new HashMap<String, String>();

            result.put("userId", user.getId().toString());
            result.put("token", user.getToken());

            return result;
        }
    }
	

    

	

	

    

    
    
}