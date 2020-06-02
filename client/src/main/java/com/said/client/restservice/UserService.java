package com.said.client.restservice;

import com.said.client.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getOneUser(long id);
    User getUserByLogin(String login);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(long id);
}
