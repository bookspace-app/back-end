package com.example.bookspace.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

	public TagOutput postTag(TagInput tagDetails) {

		User author = userRepository.getOne(tagDetails.getAuthor());
		Publication publication = publicationRepository.getOne(tagDetails.getPublication());
		Tag tag = new Tag(tagDetails, author, publication);
		tag = tagRepository.save(tag);
		author.addCreatedTag(tag);
		userRepository.save(author);
		publication.addTag(tag);
		publicationRepository.save(publication);	
		return new TagOutput(tag);
    }

	public List<Tag> getTags(){
		return tagRepository.findAll();
	}

    public Optional<Tag> getTag(Long IdTag) {
		boolean exists = tagRepository.existsById(IdTag);
		if(!exists) throw new IllegalStateException("The tag with IdTag " + IdTag + " does not exist");
        return tagRepository.findById(IdTag);
    }

	public void deleteTag(Long IdTag){
		boolean b = tagRepository.existsById(IdTag);
		if(!b) {
			throw new IllegalStateException("Tag with IdTag " + IdTag + " does not exists");
		}
		tagRepository.deleteById(IdTag);
	}

	@Transactional
	public void updateTag(Long IdTag, User author, List<Publication> tagged_publications, List<User> preferedTags) {
		Tag tag = tagRepository.findById(IdTag)
					.orElseThrow(() -> new IllegalStateException(
						"Tag with IdTag " + IdTag + " does not exist"));
		
		if (author != null &&
			!Objects.equals(tag.getAuthor(), author)){
				tag.setAuthor(author);
			}

		if (tagged_publications != null &&
			!Objects.equals(tag.getTagged_publications(), tagged_publications)){
				tag.setTagged_publications(tagged_publications);
			}

		if (preferedTags != null &&
			!Objects.equals(tag.getPreferedTags(), preferedTags)){
				tag.setPrefered_tags(preferedTags);
			}

		tagRepository.save(tag);
	}
}
