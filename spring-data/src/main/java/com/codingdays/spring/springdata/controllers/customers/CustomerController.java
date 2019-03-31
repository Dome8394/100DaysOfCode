package com.codingdays.spring.springdata.controllers.customers;

import com.codingdays.spring.springdata.business.CustomerBusinessService;
import com.codingdays.spring.springdata.entities.CustomerEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api")
public class CustomerController {

    @Autowired
    private CustomerBusinessService businessService;


    /**
     * Returns a list of all customers that are currently saved
     * in the database.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public List<CustomerEntity> getCustomers() {
        return businessService.retrieveAllCustomers();
    }

    /**
     * Returns a single customer that is found by its Id provided.
     * The id is passed as a Request parameter within the URI
     *
     * @param _id: customer ID
     * @return CustomerEntity
     */
    @RequestMapping(method = RequestMethod.GET, value = "/customer/{Id}")
    public Optional<CustomerEntity> getCustomerById(@PathVariable(value = "Id") ObjectId _id) {
        return businessService.retrieveCustomerById(_id);
    }

    /**
     * Returns a list of customers found with a given firstName
     * @param firstName: name of the customer
     * @return List of customers
     */
    @RequestMapping(method = RequestMethod.GET, value = "/customer/{firstName}")
    public List<CustomerEntity> getCustomerByFirstName(@PathVariable String firstName) {
        return businessService.retrieveCustomerByFirstName(firstName);
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
    public void saveCustomer(@RequestBody CustomerEntity customerDetails) {
         businessService.saveCustomer(customerDetails);
    }
}
