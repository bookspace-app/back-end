package com.example.bookspace.UserTests;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.models.User;
import com.example.bookspace.services.ChatService;
import com.example.bookspace.services.CommentService;
import com.example.bookspace.services.MessageService;
import com.example.bookspace.services.PublicationService;
import com.example.bookspace.services.TagService;
import com.example.bookspace.services.UserService;
 
@WebMvcTest
public class UserControllerIntTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ChatService chatService;

    @MockBean
    private CommentService CommentService;

    @MockBean
    private MessageService messageService;

    @MockBean
    private PublicationService publicationService;

    @MockBean
    private TagService tagService;
     
    @Test
    public void testgetAllUsers() throws Exception 
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
        mockMvc.perform(MockMvcRequestBuilders.get("api/users")).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }
}