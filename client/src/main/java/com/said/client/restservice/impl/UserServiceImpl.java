package com.said.client.restservice.impl;

import com.said.client.model.User;
import com.said.client.restservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    private final String urlRequest;
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeadersWithAuth;

    @Autowired
    UserServiceImpl(RestTemplate restTemplate, HttpHeaders httpHeadersWithAuth,
                    @Value("${url.request}") String urlRequest) {
        this.restTemplate = restTemplate;
        this.httpHeadersWithAuth = httpHeadersWithAuth;
        this.urlRequest = urlRequest;
    }
    @Override
    public List<User> getAllUsers() {
        // HttpEntity<String>: To get result as String.
        HttpEntity<String> entity = new HttpEntity<String>(httpHeadersWithAuth);
        ResponseEntity<User[]> response = restTemplate.exchange(urlRequest + "all" , HttpMethod.GET, entity, User[].class);
        List<User> result = Arrays.stream(response.getBody()).collect(Collectors.toList());
        return result;
    }
    @Override
    public User getOneUser(long id) {
        HttpEntity<String> entity = new HttpEntity<>(httpHeadersWithAuth);
        ResponseEntity<User> response = restTemplate.exchange(urlRequest + id, HttpMethod.GET, entity, User.class);
        User user = response.getBody();
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        HttpEntity<String> entity = new HttpEntity<>(httpHeadersWithAuth);
        ResponseEntity<User> responseEntity = restTemplate.exchange(urlRequest + "login?login=" + login, HttpMethod.GET, entity, User.class);
        return responseEntity.getBody();
    }

    @Override
    public void addUser(User user) {
        HttpEntity<User> requestBody = new HttpEntity<>(user, httpHeadersWithAuth);
        User u = restTemplate.postForObject(urlRequest + "add", requestBody, User.class);
        if (u != null && u.getEmail() != null) {
            System.out.println("Employee created: " + u.getEmail());
        } else {
            System.out.println("Something error!");
        }
    }
    @Override
    public void updateUser(User user) {
        HttpEntity<User> requestBody = new HttpEntity<>(user, httpHeadersWithAuth);
        restTemplate.exchange(urlRequest + "update", HttpMethod.PUT, requestBody, User.class );
    }
    @Override
    public void deleteUser(long id) {
        HttpEntity<User> requestBody = new HttpEntity<>(httpHeadersWithAuth);
        restTemplate.exchange(urlRequest + "delete?id=" + id, HttpMethod.DELETE, requestBody, User.class );
    }



}
