package com.codingdays.users.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @org.springframework.data.annotation.Id
    private String _id;

    private String firstName;
    private String lastName;

    public User() {
    }

    public User(String firstName, String lastName, String _id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this._id = _id;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return String.format("Customer[Id = %s, firstName = '%s', lastName = '%s']",
                _id, firstName, lastName);
    }
}
