package com.example.bookspace.PublicationTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.bookspace.Inputs.PublicationInput;
import com.example.bookspace.Output.CommentOutput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.enums.Category;
import com.example.bookspace.services.PublicationService;
import com.example.bookspace.models.User;
import com.example.bookspace.models.Comment;
import com.example.bookspace.models.Publication;
import com.example.bookspace.models.Tag;
import com.example.bookspace.repositories.UserRepository;
import com.example.bookspace.repositories.PublicationRepository;
import com.example.bookspace.repositories.TagRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class PublicationServiceTest {

    PublicationService publicationService;

    @BeforeEach
    void setup() {
        PublicationRepository publicationRepository = Mockito.mock(PublicationRepository.class);
        TagRepository tagRepository = Mockito.mock(TagRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        List<Publication> publications = new ArrayList<>();

        User u1 = new User("email1", "name1", "username1", "password1", LocalDate.now());
        List<String> fav_cat = new ArrayList<>();
        fav_cat.add("action");
        List<Category> cateogories = Category.getCategories(fav_cat);
		u1.setFavCategories(cateogories);

        List<Publication> pub = new ArrayList<>();
        List<User> exp = new ArrayList<>();
        Publication p1 = new Publication("title1", "content1", u1, Category.action);
        pub.add(p1);
        exp.add(u1);
        u1.setPublications(pub);
        p1.setLikedBy(exp);
        p1.setDislikedBy(exp);
        p1.setMentions(exp);

        Comment c1 = new Comment("content1", u1, p1);
        List<Comment> com = new ArrayList<>();
        com.add(c1);
        p1.setComments(com);

        Tag t1 = new Tag("name1", u1, p1);
        List<Tag> tag = new ArrayList<>();
        tag.add(t1);
        p1.setTags(tag);

        List<User> fav_by = new ArrayList<>();

		User u2 = new User("email2", "name2", "username2", "password2", LocalDate.now());
        List<String> fav_cat2 = new ArrayList<>();
        fav_cat2.add("romantic");
        List<Category> cateogories2 = Category.getCategories(fav_cat2);
		u2.setFavCategories(cateogories2);

        List<Publication> pub2 = new ArrayList<>();
        Publication p2 = new Publication("title2", "content2", u2, Category.romantic);
        pub2.add(p2);
        u2.setPublications(pub2);

        fav_by.add(u2);
        p1.setFavouriteBy(fav_by);

        User u3 = new User("email3", "name3", "username3", "password3", LocalDate.now());

        publications.add(p1);
        publications.add(p2);

        Optional<User> ou1 = Optional.of(u1);
        Optional<User> ou3 = Optional.of(u3);
        Optional<Publication> op1 = Optional.of(p1);

        when(publicationRepository.findAll()).thenReturn(publications);
        when(userRepository.existsById(3L)).thenReturn(true);
        when(userRepository.findById(3L)).thenReturn(ou3);
        when(publicationRepository.getOne(1L)).thenReturn(p1);
        when(publicationRepository.findById(1L)).thenReturn(op1);
        when(publicationRepository.existsById(1L)).thenReturn(true);
        when(userRepository.getOne(3L)).thenReturn(u3);
        when(userRepository.getOne(2L)).thenReturn(u2);
        when(userRepository.getOne(1L)).thenReturn(u1);


        when(userRepository.findById(1L)).thenReturn(ou1);

        this.publicationService = new PublicationService(publicationRepository, userRepository, tagRepository);
    }

    @Test
    void testgetPublications() {

        List<PublicationOutput> result = new ArrayList<>();
		result = publicationService.getPublications();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getTitle())
                        .isEqualTo("title1");
        assertThat(result.get(1).getTitle())
                        .isEqualTo("title2");
    }

    @Test
    void testpostPublication() throws Exception {

        List<Long> nl = new ArrayList<>();
        PublicationInput pi1 = new PublicationInput("title3", "content3", 3L, "romantic", nl, nl);

        PublicationOutput result;
		result = publicationService.postPublication(pi1);
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("title3");
    }

    @Test
    void testgetPublication() {

        PublicationOutput result;
		result = publicationService.getPublication(1L);
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("title1");
    }

    @Test
    void testputPublication() throws Exception {

        List<Long> nl = new ArrayList<>();
        PublicationInput pi1 = new PublicationInput("titleNEW", "content3", 3L, "potential", nl, nl);

        PublicationOutput result;
		result = publicationService.putPublication(1L, pi1);
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("titleNEW");
    }

    @Test
    void testdeletePublication() {

        publicationService.deletePublication(1L);
    }

    @Test
    void testgetLikedUsers() throws Exception {
        List<UserOutput> result = publicationService.getLikedUsers(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getEmail()).isEqualTo("email1");
    }

    @Test
    void testpostLike() throws Exception {
        PublicationOutput result = publicationService.postLike(1L, 2L);

        assertThat(result.getContent()).isEqualTo("content1");
    }

    @Test
    void testdeleteLike() throws Exception {
        publicationService.deleteLike(1L, 1L);
    }

    @Test
    void testgetDislikedUsers() throws Exception {
        List<UserOutput> result = publicationService.getDislikedUsers(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getEmail()).isEqualTo("email1");
    }

    @Test
    void testpostDislike() throws Exception {
        PublicationOutput result = publicationService.postDislike(1L, 2L);

        assertThat(result.getContent()).isEqualTo("content1");
    }

    @Test
    void testdeleteDislike() throws Exception {
        publicationService.deleteDislike(1L, 1L);
    }

    @Test
    void testgetFavUsers() {
        List<UserOutput> result = new ArrayList<>();
		result = publicationService.getFavUsers(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getUsername()).isEqualTo("username2");
    }

    @Test
    void testpostFavUsers() throws Exception {

        UserOutput result = publicationService.postFavUser(1L, 3L);

        assertThat(result.getUsername()).isEqualTo("username3");
    }

    @Test
    void testdeleteFavUser() throws Exception {
        publicationService.deleteFavUser(1L, 2L);
    }

    @Test
    void testgetComments() throws Exception {
        List<CommentOutput> result = new ArrayList<>();
		result = publicationService.getComments(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("content1");
    }

    @Test
    void testgetMentions() throws Exception {
        List<UserOutput> result = new ArrayList<>();
		result = publicationService.getMentions(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getEmail()).isEqualTo("email1");
    }

    @Test
    void testgetTags() throws Exception {
        List<TagOutput> result = new ArrayList<>();
		result = publicationService.getTags(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("name1");
    }
}