package com.example.bookspace.TagTest;

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
import com.example.bookspace.Inputs.TagInput;
import com.example.bookspace.Output.TagOutput;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TagControllerAccTest
{

    @LocalServerPort
    int randomServerPort;

    private RestTemplate restTemplate;
    private String url;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
        url = "http://localhost:" + randomServerPort + "/api/tags";
    }

    @Test
    void testgetAllTags() throws Exception {

        var responseEntity = restTemplate.getForEntity(url, ArrayList.class);
        
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
    }

    @Test
    void testgetTagByName() throws Exception {

        ResponseEntity<TagOutput> responseEntity = restTemplate.getForEntity(url + "/tagname/demoTag", TagOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.hasBody());
        assertEquals("demoTag", responseEntity.getBody().getName());
    }

    @Test
    void testpostTag() throws Exception {

        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        TagInput tagDetails = new TagInput("tagNEW", 1L, 1L);
        requestEntity = new HttpEntity<>(tagDetails, headers);

        ResponseEntity<TagOutput> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, TagOutput.class);
        assertEquals(OK, responseEntity.getStatusCode());	

        ResponseEntity<TagOutput> responseEntity2 = restTemplate.getForEntity(url + "/tagname/tagNEW", TagOutput.class);
        assertEquals(OK, responseEntity2.getStatusCode());
        assertEquals(true, responseEntity2.hasBody());
        assertEquals("tagNEW", responseEntity2.getBody().getName());
    }

    @Test
    void testupdateTag() throws Exception {

        HttpHeaders headers = new HttpHeaders();

		TagInput ti1 = new TagInput("demoTag", 1L, 1L);
        //headers.add("Accept", "application/json");

        HttpEntity<?> entity = new HttpEntity<Object>(ti1, headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(url + "/demoTag", HttpMethod.PUT, entity, Void.class);
        assertEquals(OK, responseEntity.getStatusCode());		
    }
}