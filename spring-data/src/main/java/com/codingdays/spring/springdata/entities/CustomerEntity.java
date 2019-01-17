package com.codingdays.spring.springdata.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public class CustomerEntity {


    //@GeneratedValue(strategy = GenerationType.AUTO)
    @org.springframework.data.annotation.Id
    private long Id;

    private String firstName;
    private String lastName;

    public CustomerEntity() {
    }

    public CustomerEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
        return String.format("Customer[Id = %d, firstName = '%s', lastName = '%s']",
                Id, firstName, lastName);
    }
}
