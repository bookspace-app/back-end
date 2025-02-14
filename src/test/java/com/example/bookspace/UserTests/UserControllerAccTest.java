package com.example.bookspace.UserTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        var responseEntity = restTemplate.getForEntity(url, ArrayList.class);
        
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
    }

    @Test
    void testgetToken() throws Exception {

        var responseEntity1 = restTemplate.getForEntity(url + "/token/1", Map.class);
        
        assertEquals(OK, responseEntity1.getStatusCode());
        assertEquals(true, responseEntity1.hasBody());
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
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());	
    }

    @Test
    void testloginUser() throws Exception {

        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        List<String> fav_cat = new ArrayList<>();
        fav_cat.add("action");
		UserInput u1 = new UserInput("demoEmail", "demoName", "demoUsername", "demoPassword", LocalDate.now(), "demoDescription2", fav_cat);
        requestEntity = new HttpEntity<>(u1, headers);

        var responseEntity = restTemplate.exchange(url + "/login", HttpMethod.POST, requestEntity, Map.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testlogoutUser() throws Exception {

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("auth", "AUTH");

        HttpEntity<?> entity = new HttpEntity<Object>(body, headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(url + "/1/logout", HttpMethod.POST, entity, Void.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetUserById() throws Exception {

        ResponseEntity<UserOutput> responseEntity = restTemplate.getForEntity(url + "/1", UserOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
        assertEquals("demoEmail", responseEntity.getBody().getEmail());
    }

    @Test
    void testgetUserByUsername() throws Exception {

        ResponseEntity<UserOutput> responseEntity = restTemplate.getForEntity(url + "/username/demoUsername", UserOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
        assertEquals("demoEmail", responseEntity.getBody().getEmail());
    }

    @Test
    void testupdateUser() throws Exception {

        HttpHeaders headers = new HttpHeaders();

		UserInput u1 = new UserInput("emailUpd", "nameUpd", "usernameUpd", "passwordUpd", LocalDate.now(), "descriptionUpd", null);
        //headers.add("Accept", "application/json");
        headers.add("auth", "AUTH");

        HttpEntity<?> entity = new HttpEntity<Object>(u1, headers);

        ResponseEntity<UserOutput> responseEntity = restTemplate.exchange(url + "/2", HttpMethod.PUT, entity, UserOutput.class);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());	

        ResponseEntity<UserOutput> responseEntity2 = restTemplate.getForEntity(url + "/2", UserOutput.class);
        assertEquals(OK, responseEntity2.getStatusCode());
        assertEquals(true, responseEntity2.hasBody());
        assertEquals("usernameUpd", responseEntity2.getBody().getUsername());
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

        var responseEntity = restTemplate.getForEntity(url + "/1/categories", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
        assertEquals("action", responseEntity.getBody().get(0));
    }

    @Test
    void testgetPublicationsUser() throws Exception {

        var responseEntity = restTemplate.getForEntity(url + "/1/publications", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
        assertEquals(1, responseEntity.getBody().size());
    }

    @Test
    void testgetLikedPublications() throws Exception {

        var responseEntity = restTemplate.getForEntity(url + "/1/likedPublications", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetDislikedPublications() throws Exception {

        var responseEntity = restTemplate.getForEntity(url + "/1/dislikedPublications", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetFavPublications() throws Exception {

        var responseEntity = restTemplate.getForEntity(url + "/1/favPublications", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetMentions() throws Exception {

        var responseEntity = restTemplate.getForEntity(url + "/1/mentions", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetComments() throws Exception {

        var responseEntity = restTemplate.getForEntity(url + "/1/comments", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetLikedComments() throws Exception {

        var responseEntity = restTemplate.getForEntity(url + "/1/likedComments", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetDislikedComments() throws Exception {

        var responseEntity = restTemplate.getForEntity(url + "/1/dislikedComments", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetCreatedTags() throws Exception {

        var responseEntity = restTemplate.getForEntity(url + "/1/tags", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void getFavTagsUser() throws Exception {

        var responseEntity = restTemplate.getForEntity(url + "/1/favTags", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetBlockedUsers() throws Exception {

        var responseEntity = restTemplate.getForEntity(url + "/1/blockedUsers", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testpostBlockedUsers() throws Exception {

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("auth", "AUTH");

        HttpEntity<?> entity = new HttpEntity<Object>(body, headers);

        ResponseEntity<UserOutput> responseEntity = restTemplate.exchange(url + "/2/blockedUsers/1", HttpMethod.POST, entity, UserOutput.class);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());	
    }
}