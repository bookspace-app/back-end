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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PublicationServiceTest {

    PublicationService publicationService;

    @BeforeEach
    void setup() {
        PublicationRepository publicationRepository = Mockito.mock(PublicationRepository.class);
        TagRepository tagRepository = Mockito.mock(TagRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        List<Publication> publications = new ArrayList<>();
        List<Publication> lp1 = new ArrayList<>();

        User u1 = new User("email1", "name1", "username1", "password1", LocalDate.now());
        List<String> fav_cat = new ArrayList<>();
        fav_cat.add("action");
        List<Category> cateogories = Category.getCategories(fav_cat);
		u1.setFavCategories(cateogories);
        u1.setToken("DemoToken1");

        List<Publication> pub = new ArrayList<>();
        List<User> exp = new ArrayList<>();
        Publication p1 = new Publication("title1", "content1", u1, Category.action);
        p1.getAuthor().setId(1L);
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
        lp1.add(p1);

        List<User> fav_by = new ArrayList<>();

		User u2 = new User("email2", "name2", "username2", "password2", LocalDate.now());
        List<String> fav_cat2 = new ArrayList<>();
        fav_cat2.add("romantic");
        List<Category> cateogories2 = Category.getCategories(fav_cat2);
		u2.setFavCategories(cateogories2);
        u2.setToken("DemoToken2");

        List<Publication> pub2 = new ArrayList<>();
        Publication p2 = new Publication("title2", "content2", u2, Category.romantic);
        pub2.add(p2);
        u2.setPublications(pub2);

        User u3 = new User("email3", "name3", "username3", "password3", LocalDate.now());
        Publication p3 = new Publication("title3", "content3", u3, Category.romantic);
        u3.setToken("DemoToken");

        fav_by.add(u3);

        p1.setFavouriteBy(fav_by);
        publications.add(p1);
        publications.add(p2);

        Optional<User> ou1 = Optional.of(u1);
        Optional<User> ou3 = Optional.of(u3);
        Optional<Publication> op1 = Optional.of(p1);

        when(publicationRepository.findAll()).thenReturn(publications);
        when(userRepository.existsById(3L)).thenReturn(true);
        when(userRepository.findById(3L)).thenReturn(ou3);
        when(publicationRepository.getOne(1L)).thenReturn(p1);
        when(publicationRepository.getOne(3L)).thenReturn(p3);
        when(publicationRepository.findById(1L)).thenReturn(op1);
        when(publicationRepository.existsById(1L)).thenReturn(true);
        when(publicationRepository.existsById(3L)).thenReturn(true);
        when(userRepository.getOne(3L)).thenReturn(u3);
        when(userRepository.getOne(2L)).thenReturn(u2);
        when(userRepository.getOne(1L)).thenReturn(u1);
        when(publicationRepository.save(any(Publication.class))).thenReturn(p3);
        when(publicationRepository.findByCategory(Category.getCategory("action"))).thenReturn(lp1);
        when(userRepository.save(any(User.class))).thenReturn(u3);


        when(userRepository.findById(1L)).thenReturn(ou1);

        this.publicationService = new PublicationService(publicationRepository, userRepository, tagRepository);
    }

    @Test
    void testgetPublications() throws Exception {

        List<PublicationOutput> result = new ArrayList<>();
		result = publicationService.getPublications("action");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getTitle())
                        .isEqualTo("title1");
    }

    @Test
    void testpostPublication() throws Exception {

        PublicationInput pi1 = new PublicationInput("title3", "content3", 3L, "romantic", null, null);

        PublicationOutput result;
		result = publicationService.postPublication(pi1, "DemoToken");
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

        PublicationInput pi1 = new PublicationInput("title3", "content3", 3L, "potential", null, null);

        PublicationOutput result;
		result = publicationService.putPublication(1L, pi1, "DemoToken");
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("title3");
    }

    @Test
    void testdeletePublication() {

        publicationService.deletePublication(1L, "DemoToken1");
    }

    @Test
    void testgetLikedUsers() throws Exception {
        List<UserOutput> result = publicationService.getLikedUsers(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getEmail()).isEqualTo("email1");
    }

    @Test
    void testpostLike() throws Exception {
        PublicationOutput result = publicationService.postLike(3L, 1L, "DemoToken1");

        assertThat(result.getContent()).isEqualTo("content3");
    }

    @Test
    void testdeleteLike() throws Exception {
        publicationService.deleteLike(1L, 1L, "DemoToken1");
    }

    @Test
    void testgetDislikedUsers() throws Exception {
        List<UserOutput> result = publicationService.getDislikedUsers(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getEmail()).isEqualTo("email1");
    }

    @Test
    void testpostDislike() throws Exception {
        PublicationOutput result = publicationService.postDislike(3L, 3L, "DemoToken");

        assertThat(result.getContent()).isEqualTo("content3");
    }

    @Test
    void testdeleteDislike() throws Exception {
        publicationService.deleteDislike(1L, 1L, "DemoToken1");
    }

    @Test
    void testgetFavUsers() {
        List<UserOutput> result = new ArrayList<>();
		result = publicationService.getFavUsers(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getUsername()).isEqualTo("username3");    }

    @Test
    void testpostFavUsers() throws Exception {

        UserOutput result = publicationService.postFavUser(1L, 2L, "DemoToken2");

        assertThat(result.getUsername()).isEqualTo("username3");
    }

    @Test
    void testdeleteFavUser() throws Exception {
        publicationService.deleteFavUser(1L, 3L, "DemoToken");
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