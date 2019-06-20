package com.codingdays.spring.springdata.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public class CustomerEntity {

    @org.springframework.data.annotation.Id
    private String _id;

    private String firstName;
    private String lastName;

    public CustomerEntity() {
    }

    public CustomerEntity(String firstName, String lastName, String _id) {
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
