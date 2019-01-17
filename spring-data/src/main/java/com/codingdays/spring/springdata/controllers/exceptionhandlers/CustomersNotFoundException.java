package com.codingdays.spring.springdata.controllers.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author dominik on 06.01.19
 * @project spring-data
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "There are no customers registered yet!")
public class CustomersNotFoundException extends RuntimeException {

    //TODO: lookup how proper exception handling is done in Spring
}
