package com.codingdays.spring.springdata.business;

import com.codingdays.spring.springdata.entities.CustomerEntity;
import com.codingdays.spring.springdata.repositories.customer.CustomerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dominik on 08.03.19
 * @project spring-data
 */
@Component
public class CustomerBusinessService {

    @Autowired
    private CustomerEntityRepository repository;

    /**
     * Retrieves all customers stored in the database.
     * @return List of objects with type CustomerEntity
     */
    public List<CustomerEntity> retrieveAllCustomers() {
        return repository.findAll();
    }
}
