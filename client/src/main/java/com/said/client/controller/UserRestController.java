package com.said.client.controller;

import com.said.client.model.User;
import com.said.client.restservice.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {


    @Autowired
    UserServiceImpl restTemplate;

    @GetMapping("/rest/all")
    public List<User> getAllUsers() {
        return restTemplate.getAllUsers();
    }

    @GetMapping("/rest/{id}")
    public User getOneUSer(@PathVariable("id") long id) {
        return restTemplate.getOneUser(id);
    }

    @PostMapping("/rest/add")
    public ResponseEntity addUser(@RequestBody User user) {
        restTemplate.addUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/rest/update")
    public ResponseEntity updateUser(@RequestBody User user) {
        restTemplate.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/rest/delete")
    public ResponseEntity deleteUser(@RequestParam long id) {
        restTemplate.deleteUser(id);
        return ResponseEntity.ok().build();
    }



}
