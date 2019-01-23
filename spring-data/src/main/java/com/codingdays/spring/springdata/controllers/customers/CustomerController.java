package com.codingdays.spring.springdata.controllers.customers;

import com.codingdays.spring.springdata.controllers.exceptionhandlers.CustomersNotFoundException;
import com.codingdays.spring.springdata.entities.CustomerEntity;
import com.codingdays.spring.springdata.repositories.customer.CustomerEntityRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;


@RestController
@RequestMapping(value = "/api")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private CustomerEntityRepository repository;

    @Autowired
    public CustomerController(CustomerEntityRepository repository) {
        this.repository = repository;
    }


    /**
     * Returns a list of all customers that are currently saved
     * in the database.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public Iterable<CustomerEntity> getCustomers() throws CustomersNotFoundException {

        if (isEmpty(repository.findAll())) {
            throw new CustomersNotFoundException();
        }

        return repository.findAll();
    }

    /**
     * Returns a single customer that is found by its Id provided.
     * The id is passed as a Request parameter within the URI
     *
     * @param Id: customer ID
     */
    @RequestMapping(method = RequestMethod.GET, value = "/customer/{Id}")
    public Optional<CustomerEntity> getCustomer(@PathVariable(value = "Id") Long Id) {

        log.info("==================================================");
        log.info("This is the controller for retrieving a customer by Id");
        log.info("==================================================");

        return repository.findById(Id);
    }


    /**
     * Returns a list of customers found with a given firstName
     * @param firstName: name of the customer
     * @return List of customers
     */
    @RequestMapping(method = RequestMethod.GET, value = "/customer/{firstName}")
    public List<CustomerEntity> getCustomerByFirstName(@PathVariable(value = "firstName") String firstName) {

        log.info("==================================================");
        log.info("This is the controller for retrieving a customer by name");
        log.info("==================================================");

        return repository.findAllByFirstName(firstName);
    }

    /**
     * Saves a customer Object to the database with the appropriate information provided.
     * A customer is saved with its firstname and lastname. The id of a new customer object is
     * automatically generated as specified in the CustomerEntity class.
     *
     * @param customerDetails : details of customer object as a JSON
     */
    @PostMapping
    @RequestMapping(method = RequestMethod.POST, value = "/customer/new", consumes = {"application/json",
            "application/x-www-form-urlencoded"}, produces = {"application/x-www-form-urlencoded", "application/json"})
    public String saveCustomer(@RequestBody CustomerEntity customerDetails) {

        log.info("==================================================");
        log.info("This is the controller for posting new customers");
        log.info("==================================================");

            repository.save(customerDetails);
            return customerDetails.toString();

    }
}
