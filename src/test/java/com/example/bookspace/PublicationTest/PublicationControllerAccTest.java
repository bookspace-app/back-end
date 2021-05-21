package com.example.bookspace.PublicationTest;

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
import com.example.bookspace.Inputs.PublicationInput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.UserOutput;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PublicationControllerAccTest
{

    @LocalServerPort
    int randomServerPort;

    private RestTemplate restTemplate;
    private String url;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
        url = "http://localhost:" + randomServerPort + "/api/publications";
    }

    @Test
    void testgetPublications() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url, ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
    }

    @Test
    void testpostPublication() throws Exception {

        PublicationInput p1 = new PublicationInput("titleNew", "contentNew", 1L, "romantic", null, null);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("auth", "DemoToken");

        HttpEntity<?> entity = new HttpEntity<Object>(p1, headers);

        ResponseEntity<PublicationOutput> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, PublicationOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());	

        ResponseEntity<PublicationOutput> responseEntity2 = restTemplate.getForEntity(url + "/2", PublicationOutput.class);
        assertEquals(OK, responseEntity2.getStatusCode());
        assertEquals(true, responseEntity2.hasBody());
        assertEquals("titleNew", responseEntity2.getBody().getTitle());
    }

    @Test
    void testgetPublicationById() throws Exception {

        ResponseEntity<PublicationOutput> responseEntity2 = restTemplate.getForEntity(url + "/1", PublicationOutput.class);
        assertEquals(OK, responseEntity2.getStatusCode());
        assertEquals(true, responseEntity2.hasBody());
        assertEquals("demoTitle", responseEntity2.getBody().getTitle());
    }

    @Test
    void testupdatePublication() throws Exception {

        PublicationInput p1 = new PublicationInput("demoTitle", "contentNew", 1L, "romantic", null, null);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("auth", "DemoToken");

        HttpEntity<?> entity = new HttpEntity<Object>(p1, headers);

        ResponseEntity<PublicationOutput> responseEntity = restTemplate.exchange(url + "/1", HttpMethod.PUT, entity, PublicationOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());

        ResponseEntity<PublicationOutput> responseEntity2 = restTemplate.getForEntity(url + "/1", PublicationOutput.class);
        assertEquals(OK, responseEntity2.getStatusCode());
        assertEquals(true, responseEntity2.hasBody());
        assertEquals("demoTitle", responseEntity2.getBody().getTitle());
    }

    @Test
    void testgetLikedUsers() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/like", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testpostLikedUsers() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("auth", "DemoToken");

        HttpEntity<?> entity = new HttpEntity<Object>(headers);
        
        ResponseEntity<PublicationOutput> responseEntity = restTemplate.exchange(url + "/1/like/1", HttpMethod.POST, entity, PublicationOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetDislikedUsers() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/dislike", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testpostDislikedUsers() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("auth", "DemoToken");

        HttpEntity<?> entity = new HttpEntity<Object>(headers);

        ResponseEntity<PublicationOutput> responseEntity = restTemplate.exchange(url + "/1/dislike/1", HttpMethod.POST, entity, PublicationOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetFavUsers() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/fav", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testpostFavUser() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("auth", "DemoToken");

        HttpEntity<?> entity = new HttpEntity<Object>(headers);

        ResponseEntity<UserOutput> responseEntity = restTemplate.postForEntity(url + "/1/fav/1", entity, UserOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals("demoName", responseEntity.getBody().getName());
    }

    @Test
    void testgetComments() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/comments", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetMentions() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/mentions", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetTags() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/tags", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }
}