package com.codingdays.spring.springdata.business;

import com.codingdays.spring.springdata.entities.CustomerEntity;
import com.codingdays.spring.springdata.repositories.customer.CustomerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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

    public Optional<CustomerEntity> retrieveCustomerById(long id) {
        return repository.findById(id);
    }

    public List<CustomerEntity> retrieveCustomerByFirstName(@PathVariable(value = "firstName") String firstName) {
        return repository.findAllByFirstName(firstName);
    }
}
