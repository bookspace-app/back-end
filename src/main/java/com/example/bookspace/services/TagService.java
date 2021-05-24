package com.example.bookspace.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;

import com.example.bookspace.Exceptions.DuplicateActionException;
import com.example.bookspace.Inputs.TagInput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.models.Publication;
import com.example.bookspace.models.Tag;
import com.example.bookspace.models.User;
import com.example.bookspace.repositories.PublicationRepository;
import com.example.bookspace.repositories.TagRepository;
import com.example.bookspace.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//Service class for Tag
public class TagService {

	private final TagRepository tagRepository;
	private final UserRepository userRepository;
	private final PublicationRepository publicationRepository;

	@Autowired
	public TagService(TagRepository tagRepository, UserRepository userRepository, PublicationRepository publicationRepository) {
		this.tagRepository = tagRepository;
		this.userRepository = userRepository;
		this.publicationRepository = publicationRepository;
	}

	//Given a Tag details it posts that Tag and returns it
	public TagOutput postTag(TagInput tagDetails) {
		User author = userRepository.getOne(tagDetails.getAuthorId());
		List<Tag> authorFavs = author.getFavTags();

		//If the Tag {name} already exists in DB --> Error: This tag already exists
		//Else --> Creates the new Tag doing setters where needed and saves it
		if (tagRepository.findTagByName(tagDetails.getName()).isPresent()) throw new DuplicateActionException("This tag already exists");
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

	//It returns the Tag associated with the given {tagName}
    public TagOutput getTag(String tagName) {
        Tag t = tagRepository.getOne(tagName);
		return new TagOutput(t);
    }

	//It returns the Tag associated with the given {tagName}
    public TagOutput getTagByTagName(String name) throws Exception {
		if (!tagRepository.findTagByName(name).isPresent()) throw new Exception("It does not exists a tag with tagName " + name);
		Tag tag = tagRepository.getOne(name);
		return new TagOutput(tag);
		
	}

	//It deletes the Tag associated with the given {tagName}
	public void deleteTag(String tagName) throws Exception{
		if (!tagRepository.findById(tagName).isPresent()) throw new Exception("It does not exists a tag with tagName " + tagName);
		
		Tag tag = tagRepository.getOne(tagName);

		for (Publication p: tag.getPublications())  {
			p.getTags().remove(tag);
			publicationRepository.save(p);
		}
		for (User u: tag.getFavByUsers()){
			u.getFavTags().remove(tag);
			userRepository.save(u);
		}
		
		User author = tag.getAuthor();
		author.getCreatedTags().remove(tag);
		userRepository.save(author);
				


		tagRepository.deleteById(tagName);
	}

	@Transactional
	//Given a {tagName} and some Tag Details it updates the Tag associated with the {tagName} with the given details
	public void updateTag(TagInput tagDetails) {

		//If the Tag associated with {tagName} doesn't exist --> Error: Tag with tagName " + tagName + " does not exist
		Tag tag = tagRepository.findById(tagDetails.getName())
					.orElseThrow(() -> new IllegalStateException(
						"Tag with tagName " + tagDetails.getName() + " does not exist"));
		
		//If attribute {author} is not null and not equal with the Tag actual author --> It sets the new author
		User author = userRepository.getOne(tagDetails.getAuthorId());

		if (author != null &&
			!Objects.equals(tag.getAuthor(), author)){
				tag.setAuthor(author);
			}

		tagRepository.save(tag);
	}
}
