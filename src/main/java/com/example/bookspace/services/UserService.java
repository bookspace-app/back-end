package com.example.bookspace.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.example.bookspace.Exceptions.ActionNotPermited;
import com.example.bookspace.Exceptions.AlreadyLoginException;
import com.example.bookspace.Exceptions.DuplicateActionException;
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
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.example.bookspace.Exceptions.PublicationNotFound;
import com.example.bookspace.repositories.PublicationRepository;
import com.example.bookspace.repositories.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.bytebuddy.utility.RandomString;


@Service
public class UserService {

	private final UserRepository userRepository;
	private final PublicationRepository publicationRepository;
	private final TagRepository tagRepository;
	private JavaMailSender javaMailSender;

	@Autowired
	public UserService(UserRepository userRepository, JavaMailSender javaMailSender, PublicationRepository publicationRepository, TagRepository tagRepository) {
		this.userRepository = userRepository;
		this.javaMailSender = javaMailSender;
		this.publicationRepository = publicationRepository;
		this.tagRepository = tagRepository;
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
	public UserOutput postUser(UserInput userDetails)  {
	
		if (userDetails.getEmail() == null) throw new HttpMessageConversionException("The email cannot be empty");
		else if (userRepository.findUserByEmail(userDetails.getEmail()).isPresent()) throw new HttpMessageConversionException("This email is already used");
		else if (userDetails.getPassword().equals("deactivated")) throw new HttpMessageConversionException("This password isn't safe enough");
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

		if (!userRepository.existsById(id)) throw new UserNotFoundException(id);
		User user = userRepository.getOne(id);
		if (user.getToken() != null) {
			if (user.getToken().equals(token)) {
				
				if (userDetails.getDescription() != null) user.setDescription(userDetails.getDescription());
				if (userDetails.getName() != null) user.setName(userDetails.getName());					
				if (userDetails.getUsername() != null && !userRepository.findUserByEmail(userDetails.getUsername()).isPresent()) user.setUsername(userDetails.getUsername() );		
				if (userDetails.getFavCategories() != null){
					List<Category> categories = new ArrayList<>();
					for (String cat: userDetails.getFavCategories()) {
						if (Category.existsCategory(cat)) categories.add(Category.getCategory(cat));

					}
					user.setFavCategories(categories);
				}
				user = userRepository.save(user);
				return new UserOutput(user);
			}
			else throw new IncorrectTokenException();
		}
		else throw new LoginException();		
	}

	public void deleteUser(Long userId, String token) throws IncorrectTokenException, UserNotFoundException{

		if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
		User user = userRepository.getOne(userId);

		if (user.getToken().equals(token)) userRepository.delete(user);
		else throw new IncorrectTokenException();
	}

	public Void forgotPassword(UserInput userDetails) throws UserNotFoundException {

		String email = userDetails.getEmail();
		
		if (!userRepository.findUserByEmail(email).isPresent()) throw new UserNotFoundException(email);
		User user = userRepository.getUserByEmail(email);
	
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom("bookspace.application@gmail.com");
		mail.setSubject("Your BookSpace password");
		mail.setText("Hello " + user.getName() + ", \n Somebody requested the password for the BookSpace account associated with " + user.getEmail() + ". \n No changes have been made to your account. \n Here you have your BookSpace password: " + user.getPassword() + " \n If you did not request a new password, please let us know immediately by replying to this email. \n Yours, \n The BookSpace team.");
	
		javaMailSender.send(mail);
		return null;
	}

	public Void deactivateUser(Long userId, String token) {
		
		if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
		User user = userRepository.getOne(userId);

		logout(userId, token);

		user.setPassword("deactivated");
		user.setUsername("User" + user.getId());
		user.setEmail("user." + user.getId() + "@bookspace.com");
		user.setName("User " + user.getId());
		userRepository.save(user);

		return null;
	}

	/* Reports a new publication for the user in the system 
	*/
	public Void postReportPublication(Long userId, Long publicationId, String token) throws Exception {
		
		if (!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
		User user = userRepository.getOne(userId);

		if (!user.getToken().equals(token)) throw new IncorrectTokenException();
		if (!publicationRepository.existsById(publicationId)) throw new PublicationNotFound(publicationId);
		Publication publication = publicationRepository.getOne(publicationId);

		List<Publication> reportedPublications = user.getReportedPublications();
			if (reportedPublications.contains(publication)) throw new Exception("This publication has already been reported by this User");
			else {
				user.addReportedPublication(publication);
				publication.addUserReport(user);

				if (publication.getReports().size() >= 5) {

					//Deleting the publication for users attribute {reportedPublications}
					for (User u: publication.getReports()) {
						u.removeReportedPublication(publication);
						userRepository.save(u);
					}

					//Deleting the publication for users attribute {likedPublications}
					for (User u: publication.getLikedBy()) {
						u.removeLikedPublication(publication);
						userRepository.save(u);
					}

					//Deleting the publication for users attribute {dislikedPublications}
					for (User u: publication.getDislikedBy()) {
						u.removeDislikedPublication(publication);
						userRepository.save(u);
					}

					//Deleting the publication for users attribute {favouritePublications}
					for (User u: publication.getFavouriteBy()) {
						u.removeFavPublication(publication);
						userRepository.save(u);
					}

					//Deleting the publication for users attribute {mentions}
					for (User u: publication.getMentions()) {
						u.removeMention(publication);
						userRepository.save(u);
					}

					//Deleting the publication for tag attribute {tags}
					for (Tag t: publication.getTags()) {
						t.removePublication(publication);
						tagRepository.save(t);
					}

					//Deleting the publication for users attribute {Publications}
					User author = publication.getAuthor();
					author.removePublication(publication);
					userRepository.save(author);

					publicationRepository.delete(publication);
				}
				else {
					userRepository.save(user);
					publicationRepository.save(publication);
				}
			}
		return null;
	}

	
	public String getProfilePic(Long userId) throws Exception {
		User user = userRepository.getOne(userId);
		return user.getProfilePic();
    }

	public String getProfilePicPath(Long userId) throws Exception{
		User user = userRepository.getOne(userId);
		return user.getProfilePicPath();
	}

	public UserOutput postProfilePic(Long userId, String profilePic) throws Exception {
		User user = userRepository.getOne(userId);
		user.setProfilePic(profilePic);
		user = userRepository.save(user);
		return new UserOutput(user);

	}

	private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("bookspace-app.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/firebase.json"));
        com.google.cloud.storage.Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        ((com.google.cloud.storage.Storage) storage).create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format("https://storage.googleapis.com/bookspace-app.appspot.com", URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

	public Object upload(Long id,MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = id.toString().concat(this.getExtension(fileName));  // to generated random string values for file name. 
			User u = userRepository.getOne(id);
			u.setProfilePic(fileName);
			userRepository.save(u);
			
            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            var TEMP_URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return "Successfully Uploaded !" + TEMP_URL;                     // Your customized response
        } catch (Exception e) {
            e.printStackTrace();
            return "500" + e + "Unsuccessfully Uploaded!";
        }

    }
    
    // public Object download(Long id, String fileName) throws IOException {
    //     String destFileName = id.toString().concat(this.getExtension(fileName));     // to set random strinh for destination file name
    //     String destFilePath = "" + id.toString() + "/" + destFileName;                                    // to set destination file path
        
    //     ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
    //     Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(""));
    //     com.google.cloud.storage.Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    //     com.google.cloud.storage.Blob blob = storage.get(BlobId.of("bookspace-app.appspot.com", fileName));
    //     blob.downloadTo(Paths.get(destFilePath));
    //     return "200 Successfully Downloaded!";
    // }

    public UserOutput deleteProfilePic(Long userId) throws Exception {
		User user = userRepository.getOne(userId);
		user.setProfilePic("No_pic.png");
		user = userRepository.save(user);
		return new UserOutput(user);
    }    
	
    public List<String> getFavCategoriesUser(Long id) {
		User user = userRepository.getOne(id);
		List<String> categories = new ArrayList<>();
		for (Category c: user.getFavCategories()) {
			categories.add(c.name());
		}
		return categories;
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

	public List<PublicationOutput> getLikedPublications(Long id)  {
		User author = userRepository.getOne(id);
		List<Publication> publications = author.getLikedPublications();
		List<PublicationOutput> result = new ArrayList<>();
		for (Publication p: publications) {
			result.add(new PublicationOutput(p));
		}

		return result;
    }

    public List<PublicationOutput> getDislikedPublications(Long id)  {
		User author = userRepository.getOne(id);
		List<Publication> publications = author.getDislikedPublications();
		List<PublicationOutput> result = new ArrayList<>();
		for (Publication p: publications) {
			result.add(new PublicationOutput(p));
		}
		return result;
	}
	
	public List<PublicationOutput> getFavPublications(Long id)  {
		User author = userRepository.getOne(id);
		List<Publication> publications = author.getFavouritePublications();
		List<PublicationOutput> result = new ArrayList<>();
		for (Publication p: publications) {
			result.add(new PublicationOutput(p));
		}
		return result;
	}
	
	public List<MentionOutput> getMentions(Long id)  {
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

	public List<CommentOutput> getComments(Long id)  {
		User author = userRepository.getOne(id);
		List<Comment> comments = author.getComments();
		List<CommentOutput> result = new ArrayList<>();
		for (Comment c: comments) {
			result.add(new CommentOutput(c)); //to implment
		}
		return result;
	}

    public List<CommentOutput> getLikedComments(Long id)  {
		User author = userRepository.getOne(id);
		List<Comment> comments = author.getLikedComments();
		List<CommentOutput> result = new ArrayList<>();
		for (Comment c: comments) {
			result.add(new CommentOutput(c)); //to implment
		}
		return result;    }

	public List<CommentOutput> getDislikedComments(Long id)  {
		User author = userRepository.getOne(id);
		List<Comment> comments = author.getDislikedComments();
		List<CommentOutput> result = new ArrayList<>();
		for (Comment c: comments) {
			result.add(new CommentOutput(c)); //to implment
		}
		return result;
    }

	public List<TagOutput> getCreatedTags(Long id)  {
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

    public List<UserOutput> getBlockedUsers(Long id)  {
		User user = userRepository.getOne(id);
		List<User> users = user.getBlockedUsers();
		List<UserOutput> result = new ArrayList<>();
		for (User u: users) {
			result.add(new UserOutput(u));
		}
		userRepository.save(user);
		return result;
    }

   	public UserOutput postBlockedUsers(Long id, Long blockedUserid, String token) throws UserNotFoundException  {
		
		if (!userRepository.existsById(id)) throw new UserNotFoundException(id);
		User user = userRepository.getOne(id);

		if (!userRepository.existsById(blockedUserid)) throw new UserNotFoundException(blockedUserid);
		User userToBlock = userRepository.getOne(blockedUserid);

		if (user.getToken().equals(token)) {
			if (id.equals(blockedUserid)) throw new ActionNotPermited("You cannot block yourself!");
			
			List<User> blockedUsers = user.getBlockedUsers();
			if (!blockedUsers.contains(userToBlock)){
				blockedUsers.add(userToBlock);
				user.setBlockedUsers(blockedUsers);
				user = userRepository.save(user);
				return new UserOutput(user);
			}
			else throw new DuplicateActionException("This user is already blocked");
		}
		else throw new IncorrectTokenException();
	}

    public UserOutput deleteBlockedUsers(Long id, Long blockedUserid, String token) throws UserNotFoundException  {
		
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
			else throw new ActionNotPermited("This user is not blocked yet"); 
		}
		else throw new HttpMessageConversionException("You are not authorized");

		
    }

	public UserOutput getUserByUsername(String username) throws UserNotFoundException  {
		if (!userRepository.findUserByUsername(username).isPresent()) throw new UserNotFoundException(username);
		User user = userRepository.getUserByUsername(username);
		return new UserOutput(user);
		
	}

	public UserOutput getUserByEmail(String email) throws UserNotFoundException  {
		if (!userRepository.findUserByEmail(email).isPresent()) throw new UserNotFoundException(email);
		User user = userRepository.getUserByEmail(email);
		return new UserOutput(user);
		
	}

	public Map<String, String> loginUser(UserInput userDetails) throws AlreadyLoginException, UserNotFoundException {
		
		if (userDetails.getEmail() == null) throw new HttpMessageConversionException("The mail can't be null");
		if (userDetails.getPassword() == null) throw new HttpMessageConversionException("The password can't be null");

		String email = userDetails.getEmail();

		if (!userRepository.findUserByEmail(email).isPresent()) throw new UserNotFoundException(email);
		User user = userRepository.getUserByEmail(email);
		if (user.getPassword().equals("deactivated")) throw new UserNotFoundException(email);

		if (user.getToken() != null && !user.getToken().equals("AUTH")) throw new AlreadyLoginException();
	
		if (user.getPassword().equals(userDetails.getPassword())) {

			Map<String, String> result = new HashMap<String, String>(); 
			String token = RandomString.make();
			//String token = "AUTH";
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

	public Map<String, String> getToken(Long userId) throws UserNotFoundException  {

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