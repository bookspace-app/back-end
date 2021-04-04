package com.example.bookspace.UserTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.controllers.UserController;
import com.example.bookspace.services.UserService;
import com.example.bookspace.models.User;
 
@ExtendWith(MockitoExtension.class)
public class UserControllerTest
{
    @InjectMocks
    UserController userController;
     
    @Mock
    UserService userService;
     
    @Test
    public void testgetAllUsers() 
    {
		List<UserOutput> users = new ArrayList<>();

        User u1 = new User(
                "email1",
                "demo1",
                "usernam1",
                LocalDate.now(),
                "description"
        );

		User u2 = new User(
                "email2",
                "demo2",
                "username2",
                LocalDate.now(),
                "description"
        );

		UserOutput uo1 = new UserOutput(u1);
		users.add(uo1);
		UserOutput uo2 = new UserOutput(u2);
		users.add(uo2);
 
        when(userService.getUsers()).thenReturn(users);
 
		List<UserOutput> result = new ArrayList<>();
		result = userController.getAllUsers();

        assertThat(result.size()).isEqualTo(2);
         
        assertThat(result.get(0).getUsername())
                        .isEqualTo(u1.getUsername());
         
		assertThat(result.get(1).getUsername())
                        .isEqualTo(u2.getUsername());
    }
}