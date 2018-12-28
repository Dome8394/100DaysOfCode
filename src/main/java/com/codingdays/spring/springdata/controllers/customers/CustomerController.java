package com.codingdays.spring.springdata.controllers.customers;

import com.codingdays.spring.springdata.entities.CustomerEntity;
import com.codingdays.spring.springdata.repositories.customer.CustomerEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private CustomerEntityRepository repository;

    /**
     * Retrieves a list of all customers that are currently saved
     * in the database. This method only handles get requests without
     * any request parameters
     */
    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public void getCustomers() {

        if (isEmpty(repository.findAll())) {
            System.out.println("No customers found!");
            log.info("No customers found!");

        } else {

            for (CustomerEntity customer : repository.findAll()) {
                log.info("Customers that are currently registered");
                log.info("----------------------------------------");
                log.info(customer.toString());
            }
        }
    }

    /**
     * Returns a single customer that is found by its Id provided.
     * The id is passed as a Request parameter within the URI
     */
    @RequestMapping(method = RequestMethod.GET, value = "/customers/{Id}")
    public Optional<CustomerEntity> getCustomer(@PathVariable(value = "Id") Long Id) {
        return repository.findById(Id);
    }

    /**
     * Saves a customer Object to the database with the appropriate information provided.
     * A customer is saved with its firstname and lastname. The id of a new customer object is
     * automatically generated as specified in the CustomerEntity class.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/customer/")
    public CustomerEntity saveCustomer(@RequestParam(value = "first_name") String firstName,
                                       @RequestParam(value = "last_name") String lastName) {

        return repository.save(new CustomerEntity(firstName, lastName));
    }
}
