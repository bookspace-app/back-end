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
import org.springframework.http.MediaType;

import java.util.ArrayList;
import com.example.bookspace.Inputs.PublicationInput;
import com.example.bookspace.Output.PublicationOutput;
import com.example.bookspace.Output.UserOutput;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PublicationControllerAccTest
//When it fails, it tells you that the application is not doing what the customer expects it to do
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
    void testgetAllPublications() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url, ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testpostPublication() throws Exception {

        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		PublicationInput p1 = new PublicationInput("title1", "content1", 1L, "category1");
        HttpEntity<PublicationInput> requestEntity = new HttpEntity<>(p1, headers);

        ResponseEntity<PublicationOutput> responseEntity = restTemplate.postForEntity(url, requestEntity, PublicationOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testgetPublicationById() throws Exception {

        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/1", UserOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testupdatePublication() throws Exception {

        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		PublicationInput p1 = new PublicationInput("title1", "content1", 1L, "category1");
        HttpEntity<PublicationInput> requestEntity = new HttpEntity<>(p1, headers);

        ResponseEntity responseEntity = restTemplate.exchange(url + "/1", HttpMethod.PUT, requestEntity, Void.class);
        assertEquals(400, responseEntity.getBody());
    }

    @Test
    void testgetFavUsers() throws Exception {

        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity(url + "/1/favUsers", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testpostFavUser() throws Exception {

        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UserOutput> responseEntity = restTemplate.postForEntity(url + "1/favUsers/1", requestEntity, UserOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testpostLike() throws Exception {

        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<PublicationOutput> responseEntity = restTemplate.postForEntity(url + "1/likes", requestEntity, PublicationOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testpostDislike() throws Exception {

        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<PublicationOutput> responseEntity = restTemplate.postForEntity(url + "1/dislikes", requestEntity, PublicationOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());
    }
}