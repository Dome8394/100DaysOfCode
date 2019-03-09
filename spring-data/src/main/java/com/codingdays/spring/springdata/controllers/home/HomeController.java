package com.codingdays.spring.springdata.controllers.home;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(value = "/hello-world", method = RequestMethod.GET)
    public String home() {
        return "Hello Instagram!";
    }
}
