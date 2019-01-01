package com.codingdays.spring.springdata.controllers.home;

import com.codingdays.spring.springdata.controllers.customers.CustomerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        log.info("This is the home controller");
        return "Welcome to my Spring Application using Spring Web MVC and Spring Data JPA!";


    }
}
