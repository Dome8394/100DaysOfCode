package com.codingdays.spring.springdata.entities;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;

@Document(collection = "customers")
public class CustomerEntity {

    @org.springframework.data.annotation.Id
    private long Id;

    private String firstName;
    private String lastName;

    public CustomerEntity() {
    }

    public CustomerEntity(String firstName, String lastName, long Id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.Id = Id;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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
                Id, firstName, lastName);
    }
}
