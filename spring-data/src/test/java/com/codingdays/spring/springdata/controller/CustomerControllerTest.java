package com.codingdays.spring.springdata.controller;

import com.codingdays.spring.springdata.business.CustomerBusinessService;
import com.codingdays.spring.springdata.controllers.customers.CustomerController;
import com.codingdays.spring.springdata.entities.CustomerEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author dominik on 14.01.19
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    private String expected = "[{\"firstName\": Will, \"lastName\": \"Smith\", \"id\": 1}]";

    @Autowired
    MockMvc mock;

    @MockBean
    private CustomerBusinessService businessService;

    @Test
    public void getCustomers() throws Exception {

        when(businessService.retrieveAllCustomers()).thenReturn(
                Arrays.asList(new CustomerEntity("Will", "Smith", 1))
        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/customers")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json( "[{firstName: Will, lastName: Smith, id: 1}]"))
                .andReturn();

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }



}
