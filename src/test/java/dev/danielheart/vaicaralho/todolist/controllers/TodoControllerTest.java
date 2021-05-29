package dev.danielheart.vaicaralho.todolist.controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TodoControllerTest {
    private final String baseUrl = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void guestCanRetrieveAllUsers() {
        //Prepare
        String uri = this.baseUrl + this.port + "/todos";
        System.out.println(uri);

        // Act && Assert
        String response = this.restTemplate.getForObject(uri, String.class);
        assertThat(response).contains("pageable");
    }

    @Test
    public void guestCanCreateATodo() {
        //Prepare
        String uri = this.baseUrl + this.port + "/todos";
        System.out.println(uri);
        String payload = "{\"name\":\"vai naruto \",\"description\":\"vai caralhow\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(payload, headers);
        // Act && Assert

        String response = this.restTemplate.postForObject(uri, entity, String.class);
        assertThat(response).contains("naruto");
    }

    @Test
    public void guestCanUpdateATodo() throws JSONException {
        //Prepare
        String uri = this.baseUrl + this.port + "/todos";
        System.out.println(uri);
        String payload = "{\"name\":\"vai naruto \",\"description\":\"vai caralhow\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(payload, headers);
        // Act && Assert

        String response = this.restTemplate.postForObject(uri, entity, String.class);
        JSONObject obj = new JSONObject(response);
        payload = "{\"name\":\"vai sasuke \",\"description\":\"vai caralhow\"}";
        entity = new HttpEntity<String>(payload, headers);
        uri += "/" + obj.getString("id");
        this.restTemplate.put(uri, entity);

        response = this.restTemplate.getForObject(uri, String.class);

        assertThat(response).contains("sasuke");
    }

    @Test
    public void guestCanRemoveATodo() throws JSONException {
        //Prepare
        String uri = this.baseUrl + this.port + "/todos";
        System.out.println(uri);
        String payload = "{\"name\":\"vai naruto \",\"description\":\"vai caralhow\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(payload, headers);
        // Act && Assert

        String response = this.restTemplate.postForObject(uri, entity, String.class);
        JSONObject obj = new JSONObject(response);

        uri += "/" + obj.getString("id");
        this.restTemplate.delete(uri);

    }
}