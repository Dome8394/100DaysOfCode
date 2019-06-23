package com.codingdays.users.business;

import com.codingdays.users.entities.CustomerEntity;
import com.codingdays.users.repositories.customer.CustomerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

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
     * Retrieves all users stored in the database.
     * @return List of objects with type CustomerEntity
     */
    public List<CustomerEntity> retrieveAllCustomers() {
        return repository.findAll();
    }

    /**
     * To be done
     * @param Id
     * @return
     */
    public Optional<CustomerEntity> retrieveCustomerById(String Id) {
        return repository.findById(Id);
    }

    /**
     * To be done
     * @param customerEntity
     * @return
     */
    public String saveCustomer(CustomerEntity customerEntity) {

        // first check if customer is already saved in db
        if(repository.exists(Example.of(customerEntity))) {
            return "The customer " + customerEntity.toString() + " is already registered!";
        } else {
            repository.save(customerEntity);
            return customerEntity.toString();
        }
    }
}
