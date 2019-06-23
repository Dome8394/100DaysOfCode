package com.codingdays.users.web.users;

import com.codingdays.users.business.UserBusinessServiceImpl;
import com.codingdays.users.entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class UserController {

    private final UserBusinessServiceImpl userBusinessService;

    public UserController(UserBusinessServiceImpl userBusinessService) {
        this.userBusinessService = userBusinessService;
    }


    /**
     * Returns a list of all users that are currently saved
     * in the database.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public List<User> getCustomers() {
        return userBusinessService.getUsers();
    }

    /**
     * Returns a single users that is found by its Id provided.
     * The id is passed as a Request parameter within the URI
     *
     * @param Id: users ID
     * @return User
     */
    @RequestMapping(method = RequestMethod.GET, value = "/customer/{Id}")
    public User getCustomerById(@PathVariable(value = "Id") String Id) {
        return userBusinessService.getUserById(Id);
    }

    /**
     * Saves a users Object to the database with the appropriate information provided.
     * A users is saved with its firstname and lastname. The id of a new users object is
     * automatically generated as specified in the User class.
     *
     * @param customerDetails : details of users object as a JSON
     */
    @PostMapping
    @RequestMapping(method = RequestMethod.POST, value = "/customer/new", consumes = {"application/json",
            "application/x-www-form-urlencoded"}, produces = {"application/x-www-form-urlencoded", "application/json"})
    public String saveCustomer(@RequestBody User customerDetails) {
        return userBusinessService.saveUser(customerDetails);
    }
}
