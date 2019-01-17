package com.codingdays.spring.springdata;

import com.codingdays.spring.springdata.controllers.customers.CustomerController;
import com.codingdays.spring.springdata.entities.CustomerEntity;
import com.codingdays.spring.springdata.repositories.customer.CustomerEntityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author dominik on 14.01.19
 * @project spring-data
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    @Before
    public void populateDB() {
        CustomerEntity customer = new CustomerEntity();
        customer.setFirstName("Will");
        customer.setLastName("Smith");

        customerEntityRepository.save(customer);
    }

    @Test
    public void shouldReturnSingleCustomer() throws Exception {
        this.mockMvc.perform(get("/customers/1")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().json("{'firstName':'Will', 'lastName': 'Smith'}"));
    }

}
