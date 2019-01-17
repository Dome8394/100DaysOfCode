package com.codingdays.spring.springdata.controllers.customers;

import com.codingdays.spring.springdata.controllers.exceptionhandlers.CustomersNotFoundException;
import com.codingdays.spring.springdata.entities.CustomerEntity;
import com.codingdays.spring.springdata.repositories.customer.CustomerEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;


@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private CustomerEntityRepository repository;

    @Autowired
    public CustomerController(CustomerEntityRepository repository) {
        this.repository = repository;
    }


    /**
     * Retrieves a list of all customers that are currently saved
     * in the database.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public Iterable<CustomerEntity> getCustomers() {

        if (isEmpty(repository.findAll())) {
            throw new CustomersNotFoundException();
        }

        return repository.findAll();
    }

    /**
     * Returns a single customer that is found by its Id provided.
     * The id is passed as a Request parameter within the URI
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{Id}")
    public Optional<CustomerEntity> getCustomer(@PathVariable(value = "Id") Long Id) {
        return repository.findById(Id);
    }


    /**
     * Saves a customer Object to the database with the appropriate information provided.
     * A customer is saved with its firstname and lastname. The id of a new customer object is
     * automatically generated as specified in the CustomerEntity class.
     */
    @PostMapping
    @RequestMapping(method = RequestMethod.POST, value = "/new", consumes = {"application/json",
            "application/x-www-form-urlencoded"}, produces = {"application/x-www-form-urlencoded", "application/json"})
    public String saveCustomer(@RequestBody CustomerEntity customerDetails) {

        repository.save(customerDetails);

        return customerDetails.toString();
    }
}
