package com.bookspace.bookspace.tags;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import com.bookspace.bookspace.publication.Publication;
import com.bookspace.bookspace.user.User;

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
	public void updateTag(String IdTag, User owner, Set<Publication> tagged_publications, Set<User> prefered_tags) {
		Tag tag = tagRepository.findById(IdTag)
					.orElseThrow(() -> new IllegalStateException(
						"Tag with IdTag " + IdTag + " does not exist"));
		
		if (owner != null &&
			!Objects.equals(tag.getOwner(), owner)){
				tag.setOwner(owner);
			}

		if (tagged_publications != null &&
			!Objects.equals(tag.getTagged_publications(), tagged_publications)){
				tag.setTagged_publications(tagged_publications);
			}

		if (prefered_tags != null &&
			!Objects.equals(tag.getPrefered_tags(), prefered_tags)){
				tag.setPrefered_tags(prefered_tags);
			}

		tagRepository.save(tag);
	}
}
