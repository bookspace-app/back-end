package com.example.bookspace.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;

import com.example.bookspace.models.Publication;
import com.example.bookspace.models.Tag;
import com.example.bookspace.models.User;
import com.example.bookspace.repositories.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

	private final TagRepository tagRepository;

	@Autowired
	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

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
		tagRepository.deleteById(IdTag);
	}

	@Transactional
	public void updateTag(String IdTag, User author, List<Publication> tagged_publications, List<User> preferedTags) {
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
