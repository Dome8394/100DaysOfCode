package com.codingdays.users.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @org.springframework.data.annotation.Id
    private String _id;

    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password, String _id) {
        this.username = username;
        this.password = password;
        this._id = _id;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return String.format("Customer[Id = %s, username = '%s', password = '%s']",
                _id, username, password);
    }
}
