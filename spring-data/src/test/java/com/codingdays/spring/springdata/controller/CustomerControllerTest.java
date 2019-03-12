package com.codingdays.spring.springdata.controller;

import com.codingdays.spring.springdata.business.CustomerBusinessService;
import com.codingdays.spring.springdata.controllers.customers.CustomerController;
import com.codingdays.spring.springdata.entities.CustomerEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
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
    private String expectedMultipleCustomers = "[{\"firstName\": Will, \"lastName\": \"Smith\", \"id\": 1}," +
            "{\"firstName\": Peter, \"lastName\": \"Petigrew\", \"id\": 2}," +
            "{\"firstName\": Sam, \"lastName\": \"Crow\", \"id\": 3}]";
    private String expectedNull = "[{\"firstName\": null, \"lastName\": null, \"id\": 0}]";


    @Autowired
    MockMvc mock;

    @MockBean
    private CustomerBusinessService businessService;

    /**
     * Testing if getCustomers controller responds with correct JSON.
     *
     * @throws Exception
     */
    @Test
    public void getCustomers_basic() throws Exception {

        when(businessService.retrieveAllCustomers()).thenReturn(
                Arrays.asList(new CustomerEntity("Will", "Smith", 1))
        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/customers")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{firstName: Will, lastName: Smith, id: 1}]"))
                .andReturn();

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getCustomers_multipleCustomers_basic() throws Exception {

        when(businessService.retrieveAllCustomers()).thenReturn(
                Arrays.asList(new CustomerEntity("Will", "Smith", 1),
                        new CustomerEntity("Peter", "Petigrew", 2),
                        new CustomerEntity("Sam", "Crow", 3))
        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/customers")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{firstName: Will, lastName: Smith, id: 1}," +
                        "{firstName: Peter, lastName: Petigrew, id: 2}, " +
                        "{firstName: Sam, lastName: Crow, id: 3}]"))
                .andReturn();

        JSONAssert.assertEquals(expectedMultipleCustomers, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getCustomers_multipleCustomers_nullCustomer() throws Exception {
        when(businessService.retrieveAllCustomers()).thenReturn(
                Arrays.asList(new CustomerEntity())
        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/customers")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{}]"))
                .andReturn();

        JSONAssert.assertEquals(expectedNull, result.getResponse().getContentAsString(), false);
    }

    /**
     * Testing post method
     * @throws Exception
     */
    @Test
    public void saveCustomerTest_basic() throws Exception {

        CustomerEntity customerEntity = new CustomerEntity("Will", "Smith", 1);
        JSONArray jsonObject = new JSONArray("[{\"firstName\": Will, \"lastName\": \"Smith\", \"id\": 1}]");

        when(businessService.saveCustomer(Mockito.any(CustomerEntity.class))).thenReturn(
                customerEntity.toString()
        );

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/customer/new")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toString());

        MvcResult result = mock.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }


}
