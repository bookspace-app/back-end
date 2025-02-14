package com.example.bookspace.CommentTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import java.util.ArrayList;
import com.example.bookspace.Inputs.CommentInput;
import com.example.bookspace.Output.CommentOutput;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentControllerAccTest
{

    @LocalServerPort
    int randomServerPort;

    private RestTemplate restTemplate;
    private String url;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
        url = "http://localhost:" + randomServerPort + "/api/comments";
    }

    @Test
    void testgetAllComments() throws Exception {

        var responseEntity = restTemplate.getForEntity(url, ArrayList.class);
        
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
    }

    @Test
    void testpostComment() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("auth", "AUTH");

        CommentInput commentDetails = new CommentInput("contentNEW", 1L, 1L, null, null);
        HttpEntity<?> entity = new HttpEntity<Object>(commentDetails, headers);

        ResponseEntity<CommentOutput> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, CommentOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());	

        ResponseEntity<CommentOutput> responseEntity2 = restTemplate.getForEntity(url + "/2", CommentOutput.class);
        assertEquals(OK, responseEntity2.getStatusCode());
        assertEquals(true, responseEntity2.hasBody());
        assertEquals("contentNEW", responseEntity2.getBody().getContent());
    }

    @Test
    void testgetCommentById() throws Exception {

        ResponseEntity<CommentOutput> responseEntity = restTemplate.getForEntity(url + "/1", CommentOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
        assertEquals("contentDemo", responseEntity.getBody().getContent());
    }

    @Test
    void testputComment() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("auth", "AUTH");

        CommentInput commentDetails = new CommentInput("contentDemo", 1L, null, null, null);
        HttpEntity<?> entity = new HttpEntity<Object>(commentDetails, headers);

        ResponseEntity<CommentOutput> responseEntity = restTemplate.exchange(url + "/1", HttpMethod.PUT, entity, CommentOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());	

        ResponseEntity<CommentOutput> responseEntity2 = restTemplate.getForEntity(url + "/1", CommentOutput.class);
        assertEquals(OK, responseEntity2.getStatusCode());
        assertEquals(true, responseEntity2.hasBody());
        assertEquals("contentDemo", responseEntity2.getBody().getContent());
    }

    @Test
    void testgetCommentReplies() throws Exception {

        var responseEntity = restTemplate.getForEntity(url + "/1/replies", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
    }

    @Test
    void testgetCommentMentions() throws Exception {

        var responseEntity = restTemplate.getForEntity(url + "/1/mentions", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
    }

    @Test
    void testgetLikedUsers() throws Exception {

        var responseEntity = restTemplate.getForEntity(url + "/1/like", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
    }

    @Test
    void testpostLikeUser() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("auth", "AUTH");

        HttpEntity<?> entity = new HttpEntity<Object>(headers);

        ResponseEntity<CommentOutput> responseEntity = restTemplate.exchange(url + "/1/like/1", HttpMethod.POST, entity, CommentOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());	
    }

    @Test
    void testgetDislikedUsers() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("userId", "1");

        HttpEntity<?> entity = new HttpEntity<Object>(headers);

        var responseEntity = restTemplate.exchange(url + "/1/dislike", HttpMethod.GET, entity, ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
    }

    @Test
    void testpostDislikeUser() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("auth", "AUTH");

        HttpEntity<?> entity = new HttpEntity<Object>(headers);

        ResponseEntity<CommentOutput> responseEntity = restTemplate.exchange(url + "/1/dislike/1", HttpMethod.POST, entity, CommentOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());	
    }
}