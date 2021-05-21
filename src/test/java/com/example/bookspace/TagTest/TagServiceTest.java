package com.example.bookspace.TagTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.bookspace.Inputs.TagInput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.enums.Category;
import com.example.bookspace.services.TagService;
import com.example.bookspace.models.User;
import com.example.bookspace.models.Tag;
import com.example.bookspace.models.Publication;
import com.example.bookspace.repositories.PublicationRepository;
import com.example.bookspace.repositories.TagRepository;
import com.example.bookspace.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TagServiceTest {

    TagService tagService;

    @BeforeEach
    void setup() {
        TagRepository tagRepository = Mockito.mock(TagRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        PublicationRepository publicationRepository = Mockito.mock(PublicationRepository.class);

        List<Tag> tags = new ArrayList<>();

        User u1 = new User("email1", "name1", "username1", "password1", LocalDate.now());
        Tag t1 = new Tag("demoTag1", u1);
        tags.add(t1);
        u1.setCreatedTags(tags);

        User u2 = new User("email2", "name2", "username2", "password2", LocalDate.now());
        Tag t2 = new Tag("demoTag2", u2);
        tags.add(t2);

        Optional<Tag> ot1 = Optional.empty();
        Optional<Tag> ot2 = Optional.of(t1);

        when(userRepository.getOne(1L)).thenReturn(u1);
        when(userRepository.getOne(2L)).thenReturn(u2);
        when(tagRepository.findTagByName("demoTag1")).thenReturn(ot1);
        when(tagRepository.findTagByName("demoTag2")).thenReturn(ot2);
        when(tagRepository.save(any(Tag.class))).thenReturn(t1);
        when(tagRepository.findAll()).thenReturn(tags);
        when(tagRepository.getOne(1L)).thenReturn(t1);
        when(tagRepository.getOne(2L)).thenReturn(t2);
        when(tagRepository.getTagByName("demoTag2")).thenReturn(t2);
        when(tagRepository.findById(1L)).thenReturn(ot2);

        this.tagService = new TagService(tagRepository, userRepository, publicationRepository);
    }

    @Test
    void testpostTag() throws Exception {

        TagInput ti1 = new TagInput("demoTag1", 1L, 1L);

        TagOutput result = tagService.postTag(ti1);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("demoTag1");
    }

    @Test
    void testgetTags() {

        List<TagOutput> result = new ArrayList<>();
		result = tagService.getTags();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName())
                        .isEqualTo("demoTag1");
		assertThat(result.get(1).getName())
                        .isEqualTo("demoTag2");
    }

    @Test
    void testgetTag() {

        TagOutput result;
		result = tagService.getTag(1L);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("demoTag1");
    }

    @Test
    void testTagByTagName() throws Exception {

        TagOutput result;
		result = tagService.getTagByTagName("demoTag2");
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("demoTag2");
    }

    @Test
    void testdeleteTag() {
        tagService.deleteTag(1L);
    }

    @Test
    void testupdateTag() {

        User ue1 = new User("emailEx", "nameEx", "usernameEx", "passwordEx", LocalDate.now());

        List<Publication> pe1 = new ArrayList<>();
        Publication p = new Publication("titleEx", "contentEx", ue1, Category.adult);
        pe1.add(p);

        List<User> use1 = new ArrayList<>();
        User u = new User("emaiEx1", "nameEx1", "usernameEx1", "passwordEx1", LocalDate.now());
        use1.add(u);

        tagService.updateTag(1L, ue1, pe1, use1);
    }
}