package com.example.bookspace.UserTests;

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
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.Output.UserOutput;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerAccTest
{

    @LocalServerPort
    int randomServerPort;

    private RestTemplate restTemplate;
    private String url;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
        url = "http://localhost:" + randomServerPort + "/api/users";
    }

    @Test
    void testgetAllUsers() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url, ArrayList.class);
        
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
    }

    @Test
    void testpostUser() throws Exception {

        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        List<String> fav_cat = new ArrayList<>();
        fav_cat.add("action");
		UserInput u1 = new UserInput("email1", "name1", "username1", "password1", LocalDate.now(), "description1", fav_cat);
        requestEntity = new HttpEntity<>(u1, headers);

        ResponseEntity<UserOutput> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, UserOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());	

        ResponseEntity<UserOutput> responseEntity2 = restTemplate.getForEntity(url + "/2", UserOutput.class);
        assertEquals(OK, responseEntity2.getStatusCode());
        assertEquals(true, responseEntity2.hasBody());
        assertEquals("demoEmail2", responseEntity2.getBody().getEmail());
    }

    @Test
    void testgetUserById() throws Exception {

        ResponseEntity<UserOutput> responseEntity = restTemplate.getForEntity(url + "/1", UserOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
        assertEquals("demoEmail", responseEntity.getBody().getEmail());
    }

    @Test
    void testupdateUser() throws Exception {

        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        List<String> fav_cat = new ArrayList<>();
        fav_cat.add("action");
		UserInput u1 = new UserInput("emailUpd", "nameUpd", "usernameUpd", "passwordUpd", LocalDate.now(), "descriptionUpd", fav_cat);
        requestEntity = new HttpEntity<>(u1, headers);

        ResponseEntity<UserOutput> responseEntity = restTemplate.exchange(url + "/1", HttpMethod.PUT, requestEntity, UserOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());	

        ResponseEntity<UserOutput> responseEntity2 = restTemplate.getForEntity(url + "/1", UserOutput.class);
        assertEquals(OK, responseEntity2.getStatusCode());
        assertEquals(true, responseEntity2.hasBody());
        assertEquals("emailUpd", responseEntity2.getBody().getEmail());
    }

    /*@Test
    void testgetProfilePic() throws Exception {

        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/1/profilePic", Void.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }*/

    /*@Test
    void testpostProfilePic() throws Exception {

        ResponseEntity responseEntity = restTemplate.postForEntity(url + "/1/profilePic", Void.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }*/

    @Test
    void testgetFavCategoriesUser() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/categories", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
        assertEquals("action", responseEntity.getBody().get(0));
    }

    @Test
    void testgetPublicationsUser() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/publications", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
        assertEquals(1, responseEntity.getBody().size());
    }

    @Test
    void testgetLikedPublications() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/likedPublications", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetDislikedPublications() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/dislikedPublications", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetFavPublications() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/favPublications", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetMentionedPublications() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/mentions", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetComments() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/comments", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetLikedComments() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/likedComments", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetDislikedComments() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/dislikedComments", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetCreatedTags() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/tags", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void getFavTagsUser() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/favTags", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetBlockedUsers() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/blockedUsers", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testpostBlockedUsers() throws Exception {

        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UserOutput> responseEntity = restTemplate.exchange(url + "/1/blockedUsers/2", HttpMethod.POST, requestEntity, UserOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());	
    }
}