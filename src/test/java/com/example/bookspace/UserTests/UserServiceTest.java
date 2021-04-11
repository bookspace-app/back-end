package com.example.bookspace.UserTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.TagOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.services.UserService;
import com.example.bookspace.models.User;
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

        User u1 = new User(new UserInput("email1", "name1", "username1", "password1"));
		User u2 = new User(new UserInput("email2", "name2", "username2", "password2"));

		users.add(u1);
		users.add(u2);

        Optional<User> ou1 = Optional.of(u1);
        Optional<User> ou3 = Optional.empty();

        when(userRepository.findAll()).thenReturn(users);
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.existsById(2L)).thenReturn(false);
        when(userRepository.getOne(1L)).thenReturn(u1);
        when(userRepository.findUserByEmail("email1")).thenReturn(ou1);
        when(userRepository.findUserByEmail("email3")).thenReturn(ou3);
        when(userRepository.findUserByEmail("emailNew")).thenReturn(ou3);
        when(userRepository.findUserByUsername("usernameNew")).thenReturn(ou3);
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
    void testgetUser() {

        UserOutput result;
		result = userService.getUser(1L);
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("username1");
    }

    @Test
    void testpostUser() {

        UserInput ui1 = new UserInput("email3", "name3", "username3", "password3");

        UserOutput result;
		result = userService.postUser(ui1);
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("username3");
    }

    @Test
    void testdeleteUser() {

        userService.deleteUser(1L);
    }

    @Test
    void testupdateUser() {

        byte[] pic = new byte[0];
        userService.updateUser(1L, "nameNew", "descriptionNew", "emailNew", "usernameNew", LocalDate.now(), pic);
    }

    @Test
    void testgetPublicationsUser() {

        List<PublicationOutput> result = new ArrayList<>();
		result = userService.getPublicationsUser(1L);

        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    void testgetPreferedTagsUser() {

        List<TagOutput> result = new ArrayList<>();
		result = userService.getPreferedTagsUser(1L);

        assertThat(result.size()).isEqualTo(0);
    }
}