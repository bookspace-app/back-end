package com.example.bookspace.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

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

	public UserOutput postUser(UserInput userDetails) throws Exception {
		
		if (userDetails.getEmail() == null) throw new Exception("The email can¡' be empty");
		else if (userRepository.findUserByEmail(userDetails.getEmail()).isPresent()) throw new Exception("This email is already used");
		else if (userDetails.getName() == null) throw new Exception("The name can't be empty");
		else if (userDetails.getUsername() == null) throw new Exception("The username can't be empty");
		else if (userRepository.findUserByUsername(userDetails.getUsername()).isPresent()) throw new Exception("This username is already used");
		else if (userDetails.getPassword() == null) throw new Exception("The password can't be empty");
		else if (userDetails.getDob() == null) throw new Exception("The date of birth can't be empty");
		else {
			User user = new User(userDetails.getEmail(), userDetails.getName(), userDetails.getUsername(), userDetails.getPassword(), userDetails.getDob());
			user = userRepository.save(user);
			return new UserOutput(user);
		}
    }

    public UserOutput getUser(Long id) {
		boolean exists = userRepository.existsById(id);
		if(!exists) throw new IllegalStateException("The user with id " + id + " does not exist");
		User u = userRepository.getOne(id);
		return new UserOutput(u);
    }

	@Transactional
	public UserOutput putUser(Long id, UserInput userDetails) {
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

	public void deleteUser(Long userId){
		boolean b = userRepository.existsById(userId);
		if(!b) {
			throw new IllegalStateException("User with id " + userId + " does not exists");
		}
		userRepository.deleteById(userId);

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

   	public UserOutput postBlockedUsers(Long id, Long blockedUserid) throws Exception {
		if (id == blockedUserid) throw new Exception ("You cannot block yourself!");
		User user = userRepository.getOne(id);
		User userToBlock = userRepository.getOne(blockedUserid);
		boolean b = userRepository.existsById(blockedUserid);
		if (!b){
			throw new Exception("This user doesen't exist");
		}
		List<User> blockedUsers = user.getBlockedUsers();
		if (!blockedUsers.contains(userToBlock)){
			blockedUsers.add(userToBlock);
			user.setBlockedUsers(blockedUsers);
			userRepository.save(user);
			System.out.println("User " + id + " has blocked user " + blockedUserid);
		}
		else throw new Exception("This user is already blocked");
		return new UserOutput(userToBlock);
	}

    public void deleteBlockedUsers(Long id, Long blockedUserid) throws Exception {
		User user = userRepository.getOne(id);
		User userToUnblock = userRepository.getOne(blockedUserid);
		List<User> blockedUsers = user.getBlockedUsers();
		if (blockedUsers.contains(userToUnblock)){
			blockedUsers.remove(userToUnblock);
			user.setBlockedUsers(blockedUsers);
			userRepository.save(user);
		}
		else throw new Exception("This user is not blocked yet");
    }

	public UserOutput getUserByUsername(String username) throws Exception {
		if (!userRepository.findUserByUsername(username).isPresent()) throw new Exception("It does not exists a user with username " + username);
		User user = userRepository.getUserByUsername(username);
		return new UserOutput(user);
		
	}

	

    

	

	

    

    
    
}
