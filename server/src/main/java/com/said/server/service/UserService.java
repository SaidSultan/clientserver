package com.said.server.service;

import com.said.server.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> getListUsers();
    void updateUser(User user);
    void deleteUserById(long id);
    User getUserById(long id);
    User getUserByLogin(String login);
}
