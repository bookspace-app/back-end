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
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.ArrayList;

import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.Output.UserOutput;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerAccTest {

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

        ResponseEntity responseEntity = restTemplate.getForEntity(url, ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
    }

    @Test
    void testgetUserById() throws Exception {

        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/1", UserOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
    }

    @Test
    void testgetPublicationsUser() throws Exception {

        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/1/publications", ArrayList.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
    }

    @Test
    void testregisterNewUser() throws Exception {

        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		UserInput u1 = new UserInput("email", "demo", "username", LocalDate.now(), "description");
        HttpEntity<UserInput> requestEntity = new HttpEntity<>(u1, headers);

        ResponseEntity<UserInput> responseEntity = restTemplate.postForEntity(url, requestEntity, UserInput.class);
        System.out.println("Status Code: " + responseEntity.getStatusCode());		
    }
}