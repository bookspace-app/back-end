package com.example.bookspace.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;

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

	public TagOutput postTag(TagInput tagDetails) throws Exception {
		User author = userRepository.getOne(tagDetails.getAuthorId());
		List<Tag> authorFavs = author.getFavTags();
		if (tagRepository.findTagByName(tagDetails.getName()).isPresent()) throw new Exception ("This tag already exists");
		else {
			Tag tag = new Tag(tagDetails.getName(), author);
			authorFavs.add(tag);
			tag = tagRepository.save(tag);
			author = userRepository.save(author);
			return new TagOutput(tag);
		}
    }

	public List<TagOutput> getTags(){
		
		List<TagOutput> result = new ArrayList<>();
		for (Tag t: tagRepository.findAll()) {
			TagOutput to = new TagOutput(t);
			result.add(to);
		}
		return result;
	}

    public TagOutput getTag(Long idTag) {
        Tag t = tagRepository.getOne(idTag);
		return new TagOutput(t);
    }

	public void deleteTag(Long IdTag){
		tagRepository.deleteById(IdTag);
	}

	@Transactional
	public void updateTag(Long IdTag, User author, List<Publication> publications, List<User> users) {
		Tag tag = tagRepository.findById(IdTag)
					.orElseThrow(() -> new IllegalStateException(
						"Tag with IdTag " + IdTag + " does not exist"));
		
		if (author != null &&
			!Objects.equals(tag.getAuthor(), author)){
				tag.setAuthor(author);
			}

		if (publications != null &&
			!Objects.equals(tag.getPublications(), publications)){
				tag.setPublications(publications);
			}

		if (users != null &&
			!Objects.equals(tag.getUsers(), users)){
				tag.setUsers(users);
			}

		tagRepository.save(tag);
	}
}
