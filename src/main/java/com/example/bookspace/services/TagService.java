package com.example.bookspace.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;

import com.example.bookspace.models.Publication;
import com.example.bookspace.models.Tag;
import com.example.bookspace.models.User;
import com.example.bookspace.repositories.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//Service class for Tag
public class TagService {

	private final TagRepository tagRepository;

	@Autowired
	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

<<<<<<< HEAD
	public List<Tag> getTags(){
		return tagRepository.findAll();
	}

    public Optional<Tag> getTag(String IdTag) {
		boolean exists = tagRepository.existsById(IdTag);
		if(!exists) throw new IllegalStateException("The tag with IdTag " + IdTag + " does not exist");
        return tagRepository.findById(IdTag);
    }

	public void deleteTag(String IdTag){
		boolean b = tagRepository.existsById(IdTag);
		if(!b) {
			throw new IllegalStateException("Tag with IdTag " + IdTag + " does not exists");
		}
=======
	//Given a Tag details it posts that Tag and returns it
	public TagOutput postTag(TagInput tagDetails) throws Exception {
		User author = userRepository.getOne(tagDetails.getAuthorId());
		List<Tag> authorFavs = author.getFavTags();

		//If the Tag {name} already exists in DB --> Error: This tag already exists
		//Else --> Creates the new Tag doing setters where needed and saves it
		if (tagRepository.findTagByName(tagDetails.getName()).isPresent()) throw new Exception ("This tag already exists");
		else {
			Tag tag = new Tag(tagDetails.getName(), author);
			authorFavs.add(tag);
			tag = tagRepository.save(tag);
			author = userRepository.save(author);
			return new TagOutput(tag);
		}
    }

	//It returns with a list all Tags in the DB
	public List<TagOutput> getTags(){
		
		List<TagOutput> result = new ArrayList<>();
		//For every Tag in the DB we transform them into TagOutput and return the list
		for (Tag t: tagRepository.findAll()) {
			TagOutput to = new TagOutput(t);
			result.add(to);
		}
		return result;
	}

	//It returns the Tag associated with the given {idTag}
    public TagOutput getTag(Long idTag) {
        Tag t = tagRepository.getOne(idTag);
		return new TagOutput(t);
    }

	//It deletes the Tag associated with the given {idTag}
	public void deleteTag(Long IdTag){
>>>>>>> development
		tagRepository.deleteById(IdTag);
	}

	@Transactional
<<<<<<< HEAD
	public void updateTag(String IdTag, User author, List<Publication> tagged_publications, List<User> preferedTags) {
=======
	//Given a {idTag} and some Tag Details it updates the Tag associated with the {idTag} with the given details
	public void updateTag(Long IdTag, User author, List<Publication> publications, List<User> users) {

		//If the Tag associated with {idTag} doesn't exist --> Error: Tag with IdTag " + IdTag + " does not exist
>>>>>>> development
		Tag tag = tagRepository.findById(IdTag)
					.orElseThrow(() -> new IllegalStateException(
						"Tag with IdTag " + IdTag + " does not exist"));
		
		//If attribute {author} is not null and not equal with the Tag actual author --> It sets the new author
		if (author != null &&
			!Objects.equals(tag.getAuthor(), author)){
				tag.setAuthor(author);
			}

		//If attribute {publications} is not null and not equal with the Tag actual publications --> It sets the new publications
		if (publications != null &&
			!Objects.equals(tag.getPublications(), publications)){
				tag.setPublications(publications);
			}

		//If attribute {users} is not null and not equal with the Tag actual users --> It sets the new users
		if (users != null &&
			!Objects.equals(tag.getUsers(), users)){
				tag.setUsers(users);
			}

		tagRepository.save(tag);
	}
}
