package com.example.bookspace.CommentTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.bookspace.Inputs.CommentInput;
import com.example.bookspace.Output.CommentOutput;
import com.example.bookspace.Output.UserOutput;
import com.example.bookspace.enums.Category;
import com.example.bookspace.services.CommentService;
import com.example.bookspace.models.User;
import com.example.bookspace.models.Comment;
import com.example.bookspace.models.Publication;
import com.example.bookspace.repositories.UserRepository;
import com.example.bookspace.repositories.CommentRepository;
import com.example.bookspace.repositories.PublicationRepository;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CommentServiceTest {

    CommentService commentService;

    @BeforeEach
    void setup() {
        CommentRepository commentRepository = Mockito.mock(CommentRepository.class);
        PublicationRepository publicationRepository = Mockito.mock(PublicationRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        List<Comment> comments = new ArrayList<>();
        List<Comment> reply = new ArrayList<>();
        List<User> mentions = new ArrayList<>();

        User u1 = new User("email1", "name1", "username1", "password1", LocalDate.now());
        u1.setToken("DemoToken");
        Publication p1 = new Publication("title1", "content1", u1, Category.action);
        Comment c1 = new Comment("content1", u1, p1);
        c1.getAuthor().setId(1L);
        c1.getPublication().setId(1L);
        c1.getAuthor().setToken("DemoToken");
        Comment cr1 = new Comment("contentr1", u1, p1);
        reply.add(cr1);
        c1.setReplies(reply);
        Comment nc1 = new Comment("contentNEW", u1, p1);

        User u2 = new User("email2", "name2", "username2", "password2", LocalDate.now());
        c1.addLikedBy(u2);
        c1.addDislikedBy(u2);
        Publication p2 = new Publication("title2", "content2", u2, Category.action);
        Comment c2 = new Comment("content2", u2, p2);

        comments.add(c1);
        comments.add(c2);
        mentions.add(u2);

        c1.setCommentMentions(mentions);

        when(commentRepository.findAll()).thenReturn(comments);
        when(userRepository.getOne(1L)).thenReturn(u1);
        when(userRepository.getOne(2L)).thenReturn(u2);
        when(userRepository.existsById(1L)).thenReturn(true);
        when(publicationRepository.getOne(1L)).thenReturn(p1);
        when(publicationRepository.existsById(1L)).thenReturn(true);
        when(commentRepository.existsById(1L)).thenReturn(true);
        when(commentRepository.getOne(1L)).thenReturn(c1);
        when(commentRepository.save(any(Comment.class))).thenReturn(nc1);
        when(commentRepository.save(c1)).thenReturn(c1);

        this.commentService = new CommentService(commentRepository, userRepository, publicationRepository);
    }

    @Test
    void testgetComments() {

        List<CommentOutput> result = new ArrayList<>();
		result = commentService.getComments();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getContent())
                        .isEqualTo("content1");
        assertThat(result.get(1).getContent())
                        .isEqualTo("content2");
    }

    @Test
    void testpostComment() throws Exception {

        List<String> ment = new ArrayList<>();
        ment.add("username1");
        CommentInput ci1 = new CommentInput("contentNEW", 1L, 1L, 1L, ment);
        CommentOutput result = commentService.postComment(ci1, "DemoToken");
        assertThat(result).isNotNull();
        assertThat(result.getContent()).isEqualTo("contentNEW");
    }

    @Test
    void testgetComment() {

        CommentOutput result;
		result = commentService.getComment(1L);
        assertThat(result).isNotNull();
        assertThat(result.getContent()).isEqualTo("content1");
    }

    @Test
    void testputComment() throws Exception {

        List<Long> ment = new ArrayList<>();
        ment.add(1L);
        CommentInput ci1 = new CommentInput("contentNEW", 1L, null, null, null);

        CommentOutput result;
		result = commentService.putComment(1L, ci1, "DemoToken");

        assertThat(result).isNotNull();
        assertThat(result.getContent()).isEqualTo("contentNEW");
    }

    @Test
    void testdeleteComment() {
        commentService.deleteComment(1L, "DemoToken");
    }

    @Test
    void testgetCommentReplies() {
        List<CommentOutput> result = commentService.getCommentReplies(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getContent()).isEqualTo("contentr1");
    }

    @Test
    void testgetLikedUsers() {
        List<UserOutput> result = commentService.getLikedUsers(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getEmail()).isEqualTo("email2");
    }

    @Test
    void testpostLikeComment() throws Exception {

        CommentOutput result = commentService.postLikeComment(1L, 1L, "DemoToken");
        assertThat(result).isNotNull();
        assertThat(result.getContent()).isEqualTo("content1");
    }

    @Test
    void testdeleteLikeComment() {

        CommentOutput result = commentService.deleteLikeComment(1L, 1L, "DemoToken");

        assertThat(result).isNotNull();
        assertThat(result.getContent()).isEqualTo("content1");
    }

    @Test
    void testgetDislikedUsers() {
        List<UserOutput> result = commentService.getDislikedUsers(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getEmail()).isEqualTo("email2");
    }

    @Test
    void testpostDislikeComment() throws Exception {

        CommentOutput result = commentService.postDislikeComment(1L, 1L, "DemoToken");
        assertThat(result).isNotNull();
        assertThat(result.getContent()).isEqualTo("content1");
    }

    @Test
    void testdeleteDislikeComment() {

        CommentOutput result = commentService.deleteDislikeComment(1L, 1L, "DemoToken");

        assertThat(result).isNotNull();
        assertThat(result.getContent()).isEqualTo("content1");
    }

    @Test
    void testgetCommentMentions() {
        List<UserOutput> result = commentService.getCommentMentions(1L);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getEmail()).isEqualTo("email2");
    }
}