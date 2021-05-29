package com.example.bookspace.UserTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.Output.CommentOutput;
import com.example.bookspace.Output.MentionOutput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.enums.Category;
import com.example.bookspace.services.UserService;
import com.example.bookspace.models.User;
import com.example.bookspace.models.Tag;
import com.example.bookspace.models.Comment;
import com.example.bookspace.models.Publication;
import com.example.bookspace.repositories.PublicationRepository;
import com.example.bookspace.repositories.TagRepository;
import com.example.bookspace.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//User service methods test
class UserServiceTest {

    UserService userService;

    @BeforeEach
    //Before running our tests we set up with mockito library the external calls result
    void setup() {

        //Mock the userRepository so we simulate the returns
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        PublicationRepository publicationRepository = Mockito.mock(PublicationRepository.class);
        TagRepository tagRepository = Mockito.mock(TagRepository.class);
        JavaMailSender javaMailSender = Mockito.mock(JavaMailSender.class);

        List<User> users = new ArrayList<>();

        User u1 = new User("email1", "name1", "username1", "password1", LocalDate.now());
        u1.setToken("TokenTest");
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
        u1.setId(1L);

        List<Comment> com = new ArrayList<>();
        Comment c = new Comment("commentExp", u1, p);
        com.add(c);
        u1.setComments(com);
        u1.setLikedComments(com);
        u1.setDislikedComments(com);

        List<Tag> tgs = new ArrayList<>();
        Tag t = new Tag("name1", u1);
        tgs.add(t);
        u1.setfavTags(tgs);
        u1.setCreatedTags(tgs);

		User u2 = new User("email2", "name2", "username2", "password2", LocalDate.now());
        User u3 = new User("email3", "name3", "username3", "password3", LocalDate.now());
        User u4 = new User("email3", "name3", "username3", "password3", LocalDate.of(1998, 12, 2));
        u4.setId(10L);

        List<User> bloks = new ArrayList<>();
        bloks.add(u2);
        u1.setBlockedUsers(bloks);

		users.add(u1);
		users.add(u2);

        Optional<User> ou1 = Optional.of(u1);
        Optional<User> ou3 = Optional.empty();

        //Here we return correctly in every userRepository call
        when(userRepository.findAll()).thenReturn(users);
        when(userRepository.findUserByEmail("email3")).thenReturn(ou3);
        when(userRepository.findUserByEmail("email1")).thenReturn(ou1);
        when(userRepository.findUserByUsername("username3")).thenReturn(ou3);
        when(userRepository.findUserByUsername("username1")).thenReturn(ou1);
        when(userRepository.getUserByUsername("username1")).thenReturn(u1);
        when(userRepository.getUserByEmail("email1")).thenReturn(u1);
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.getOne(1L)).thenReturn(u1);
        when(userRepository.getOne(2L)).thenReturn(u2);
        when(userRepository.getOne(3L)).thenReturn(u3);
        when(userRepository.existsById(3L)).thenReturn(true);
        when(userRepository.findById(1L)).thenReturn(ou1);
        when(userRepository.save(u1)).thenReturn(u1);
        when(userRepository.save(any(User.class))).thenReturn(u4);

        this.userService = new UserService(userRepository, javaMailSender, publicationRepository, tagRepository);
    }

    @Test
    //Tests if the service returns all users in the DB
    void testgetUsers() {

        List<UserOutput> result = new ArrayList<>();
		result = userService.getUsers();

        //It passes the test --> if returned 2 users with the correct username 
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getUsername())
                        .isEqualTo("username1");
		assertThat(result.get(1).getUsername())
                        .isEqualTo("username2");
    }

    @Test
    //Tests if the service posts correctly the user in the DB
    void testpostUser() throws Exception {

        List<String> fav_cat = new ArrayList<>();
        UserInput ui1 = new UserInput("email3", "name3", "username3", "password3", LocalDate.of(1998, 12, 2), "description3", fav_cat);

        UserOutput result = userService.postUser(ui1);

        //It passes the test --> if not null returned and user has the correct username 
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("username3");
    }

    @Test
    //Tests if the service returns the user asked in the DB
    void testgetUser() {

        UserOutput result;
		result = userService.getUser(1L);

        //It passes the test --> if not null returned and user has the correct username
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("username1");
    }

    @Test
    //Tests if the service updates the user asked in the DB
    void testputUser() {

        List<String> fav_cat = new ArrayList<>();
        UserInput ui1 = new UserInput("email3", "name3", "username3", "password3", LocalDate.of(1998, 12, 2), "description3", fav_cat);
        

        UserOutput result;
		result = userService.putUser(1L, ui1, "TokenTest");

        //It passes the test --> if not null returned and user has the correct updated username
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("username3");
    }

    @Test
    //Tests if the service deletes the user asked in the DB
    void testdeleteUser() {

        //It passes the test --> if there is no exceptions in the call
        userService.deleteUser(1L, "TokenTest");
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
    //Tests if the service gets the favourite categories of the user asked in the DB
    void testgetFavCategoriesUser() {
        List<String> result = userService.getFavCategoriesUser(1L);

        //It passes the test --> if the list size is 1 as created and the category is correct
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo("action");
    }

    @Test
    //Tests if the service gets the publications posted by the user asked in the DB
    void testgetPublicationsUser() {
        List<PublicationOutput> result = userService.getPublicationsUser(1L);

        //It passes the test --> if the list size is 1 as created and the content of the publication is correct
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("content");
    }

    @Test
    //Tests if the service gets the liked publications of the user asked in the DB
    void testgetLikedPublications() throws Exception {
        List<PublicationOutput> result = userService.getLikedPublications(1L);

        //It passes the test --> if the list size is 1 as created and the content of the publication liked is correct
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("content");
    }

    @Test
    //Tests if the service gets the disliked publications of the user asked in the DB
    void testgetDislikedPublications() throws Exception {
        List<PublicationOutput> result = userService.getDislikedPublications(1L);

        //It passes the test --> if the list size is 1 as created and the content of the publication disliked is correct
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("content");
    }

    @Test
    //Tests if the service gets the favourite publications of the user asked in the DB
    void testgetFavPublications() throws Exception {
        List<PublicationOutput> result = userService.getFavPublications(1L);

        //It passes the test --> if the list size is 1 as created and the content of the publication favourited is correct
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("content");
    }

    @Test
    //Tests if the service gets the mentions of the user asked in the DB
    void testgetMentions() throws Exception {
        List<MentionOutput> result = userService.getMentions(1L);

        //It passes the test --> if the list size is 1 as created and the content of the publication where mentioned is correct
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("content");
    }

    @Test
    //Tests if the service gets the comments of the user asked in the DB
    void testgetComments() throws Exception {
        List<CommentOutput> result = userService.getComments(1L);

        //It passes the test --> if the list size is 1 as created and the content of the comment is correct
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("commentExp");
    }

    @Test
    //Tests if the service gets the liked comments of the user asked in the DB
    void testgetLikedComments() throws Exception {
        List<CommentOutput> result = userService.getLikedComments(1L);

        //It passes the test --> if the list size is 1 as created and the content of the liked comment is correct
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("commentExp");
    }

    @Test
    //Tests if the service gets the disliked comments of the user asked in the DB
    void testgetDislikedComments() throws Exception {
        List<CommentOutput> result = userService.getDislikedComments(1L);

        //It passes the test --> if the list size is 1 as created and the content of the disliked comment is correct
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("commentExp");
    }

    @Test
    //Tests if the service gets the created tags of the user asked in the DB
    void testgetCreatedTags() throws Exception {
        List<TagOutput> result = userService.getCreatedTags(1L);

        //It passes the test --> if the list size is 1 as created and the name of the created tag is correct
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("name1");
    }

    @Test
    //Tests if the service gets the favourite tags of the user asked in the DB
    void testgetFavTagsUser() {
        List<TagOutput> result = new ArrayList<>();
		result = userService.getFavTagsUser(1L);

        //It passes the test --> if the list size is 1 as created and the name of the liked tag is correct
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("name1");
    }

    @Test
    //Tests if the service gets the blocked users of the user asked in the DB
    void testgetBlockedUsers() throws Exception {
        List<UserOutput> result = userService.getBlockedUsers(1L);

        //It passes the test --> if the list size is 1 as created and the email of the blocked user is correct
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getEmail()).isEqualTo("email2");
    }

    @Test
    //Tests if the service posts a blocked user for the user asked
    void testpostBlockedUsers() throws Exception {
        UserOutput result = userService.postBlockedUsers(1L, 3L, "TokenTest");

        //It passes the test --> if the blocked user created email is correct
        assertThat(result.getEmail()).isEqualTo("email3");
    }

    @Test
    //Tests if the service deleted a blocked user for the user asked in the DB
    void testdeleteBlockedUsers() throws Exception {
        userService.postBlockedUsers(1L, 3L, "TokenTest");

        //It passes the test --> if there is no exceptions executing it
        userService.deleteBlockedUsers(1L, 3L, "TokenTest");
    }

    @Test
    //Tests if the service gets user of the DB by its username
    void testgetUserByUsername() throws Exception {
        UserOutput result = userService.getUserByUsername("username1");

        //It passes the test --> if the user email is correct
        assertThat(result.getEmail()).isEqualTo("email1");
    }

    @Test
    //Tests if the service can login a user
    void testloginUser() throws Exception {
        UserInput loginUser = new UserInput("email1", "name1", "username1", "password1", LocalDate.of(1998, 12, 2), "description1", null);
        userService.logout(1L, "TokenTest");
        Map<String, String> result = userService.loginUser(loginUser);

        //It passes the test --> if the map returned is not null
        assertThat(result.values()).isNotEmpty();
    }

    @Test
    //Tests if the service can logou a user
    void testlogout() throws Exception {

        //It passes the test --> if there is no exceptions executing it
        userService.logout(1L, "TokenTest");
    }

    @Test
    //Tests if the service gets the token of the loged user asked in the DB
    void testgetToken() throws Exception {

        Map<String, String> result = userService.getToken(1L);

        //It passes the test --> if the map returned is not empty
        assertThat(result.values()).isNotEmpty();
    }
}