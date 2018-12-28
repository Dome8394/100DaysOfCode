package com.codingdays.spring.springdata.controllers.home;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "Welcome to my Spring Application using Spring Web MVC and Spring Data JPA!";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping(value = "*", method = RequestMethod.GET)
    public String noContent() {
        return "No content!";
    }
}
