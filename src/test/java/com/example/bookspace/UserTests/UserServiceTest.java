package com.example.bookspace.UserTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.Output.CommentOutput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.enums.Category;
import com.example.bookspace.services.UserService;
import com.example.bookspace.models.User;
import com.example.bookspace.models.Tag;
import com.example.bookspace.models.Comment;
import com.example.bookspace.models.Publication;
import com.example.bookspace.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class UserServiceTest {

    UserService userService;

    @BeforeEach
    void setup() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        List<User> users = new ArrayList<>();

        User u1 = new User("email1", "name1", "username1", "password1", LocalDate.now());
        List<String> fav_cat = new ArrayList<>();
        fav_cat.add("action");
        List<Category> cateogories = Category.getCategories(fav_cat);
		u1.setFavCategories(cateogories);

        List<Publication> pub = new ArrayList<>();
        Publication p = new Publication("title", "content", u1, Category.action);
        pub.add(p);
        u1.setPublications(pub);
        u1.setLikedPublications(pub);
        u1.setDislikedPublications(pub);
        u1.setFavouritePublications(pub);
        u1.setMentions(pub);

        List<Comment> com = new ArrayList<>();
        Comment c = new Comment("commentExp", u1, p);
        com.add(c);
        u1.setComments(com);
        u1.setLikedComments(com);
        u1.setDislikedComments(com);

        List<Tag> tgs = new ArrayList<>();
        Tag t = new Tag("name1", u1, p);
        tgs.add(t);
        u1.setfavTags(tgs);
        u1.setCreatedTags(tgs);

		User u2 = new User("email2", "name2", "username2", "password2", LocalDate.now());
        User u3 = new User("email3", "name3", "username3", "password3", LocalDate.now());

        List<User> bloks = new ArrayList<>();
        bloks.add(u2);
        u1.setBlockedUsers(bloks);

		users.add(u1);
		users.add(u2);

        Optional<User> ou1 = Optional.of(u1);
        Optional<User> ou3 = Optional.empty();

        when(userRepository.findAll()).thenReturn(users);
        when(userRepository.findUserByEmail("email3")).thenReturn(ou3);
        when(userRepository.findUserByUsername("username3")).thenReturn(ou3);
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.getOne(1L)).thenReturn(u1);
        when(userRepository.getOne(2L)).thenReturn(u2);
        when(userRepository.getOne(3L)).thenReturn(u3);
        when(userRepository.existsById(3L)).thenReturn(true);
        when(userRepository.findById(1L)).thenReturn(ou1);

        this.userService = new UserService(userRepository);
    }

    @Test
    void testgetUsers() {

        List<UserOutput> result = new ArrayList<>();
		result = userService.getUsers();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getUsername())
                        .isEqualTo("username1");
		assertThat(result.get(1).getUsername())
                        .isEqualTo("username2");
    }

    @Test
    void testpostUser() throws Exception {

        List<String> fav_cat = new ArrayList<>();
        UserInput ui1 = new UserInput("email3", "name3", "username3", "password3", LocalDate.now(), "description3", fav_cat);

        UserOutput result = userService.postUser(ui1);
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("username3");
    }

    @Test
    void testgetUser() {

        UserOutput result;
		result = userService.getUser(1L);
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("username1");
    }

    @Test
    void testputUser() {

        List<String> fav_cat = new ArrayList<>();
        UserInput ui1 = new UserInput("email3", "name3", "username3", "password3", LocalDate.now(), "description3", fav_cat);

        UserOutput result;
		result = userService.putUser(1L, ui1);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("username3");
    }

    @Test
    void testdeleteUser() {
        userService.deleteUser(1L);
    }

    @Test
    void testgetProfilePic() {
        //Not implemented yet
    }

    @Test
    void testpostProfilePic() {
        //Not implemented yet
    }

    @Test
    void testdeleteProfilePic() {
        //Not implemented yet
    }

    @Test
    void testgetFavCategoriesUser() {
        List<String> result = userService.getFavCategoriesUser(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo("action");
    }

    @Test
    void testgetPublicationsUser() {
        List<PublicationOutput> result = userService.getPublicationsUser(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("content");
    }

    @Test
    void testgetLikedPublications() throws Exception {
        List<PublicationOutput> result = userService.getLikedPublications(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("content");
    }

    @Test
    void testgetDislikedPublications() throws Exception {
        List<PublicationOutput> result = userService.getDislikedPublications(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("content");
    }

    @Test
    void testgetFavPublications() throws Exception {
        List<PublicationOutput> result = userService.getFavPublications(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("content");
    }

    @Test
    void testgetMentionedPublications() throws Exception {
        List<PublicationOutput> result = userService.getMentionedPublications(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("content");
    }

    @Test
    void testgetComments() throws Exception {
        List<CommentOutput> result = userService.getComments(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("commentExp");
    }

    @Test
    void testgetLikedComments() throws Exception {
        List<CommentOutput> result = userService.getLikedComments(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("commentExp");
    }

    @Test
    void testgetDislikedComments() throws Exception {
        List<CommentOutput> result = userService.getDislikedComments(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("commentExp");
    }

    @Test
    void testgetCreatedTags() throws Exception {
        List<TagOutput> result = userService.getCreatedTags(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("name1");
    }

    @Test
    void testgetFavTagsUser() {
        List<TagOutput> result = new ArrayList<>();
		result = userService.getFavTagsUser(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("name1");
    }

    @Test
    void testgetBlockedUsers() throws Exception {
        List<UserOutput> result = userService.getBlockedUsers(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getEmail()).isEqualTo("email2");
    }

    @Test
    void testpostBlockedUsers() throws Exception {
        UserOutput result = userService.postBlockedUsers(1L, 3L);

        assertThat(result.getEmail()).isEqualTo("email3");
    }

    @Test
    void testdeleteBlockedUsers() throws Exception {
        userService.deleteBlockedUsers(1L, 2L);
    }
}