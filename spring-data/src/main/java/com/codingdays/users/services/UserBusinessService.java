package com.codingdays.users.services;

import com.codingdays.users.entities.User;

import java.util.List;

public interface UserBusinessService {

    List<User> getUsers();
    User getUserById(String Id);
    String saveUser(User user);
}
