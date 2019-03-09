package com.codingdays.spring.springdata;

import com.codingdays.spring.springdata.business.CustomerBusinessService;
import com.codingdays.spring.springdata.entities.CustomerEntity;
import com.codingdays.spring.springdata.repositories.customer.CustomerEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author dominik on 08.03.19
 * @project spring-data
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerBusinessServiceTest {

    @InjectMocks
    private CustomerBusinessService businessService;

    @Mock
    private CustomerEntityRepository repository;

    @Test
    public void retrieveAllCustomers_basic() {

        when(repository.findAll()).thenReturn(Arrays.asList(
                new CustomerEntity("Will", "Smith", 1)
        ));

        List<CustomerEntity> items = businessService.retrieveAllCustomers();

        assertEquals("Will", items.get(0).getFirstName()); // true
        assertEquals("Smith", items.get(0).getLastName()); // true
        assertEquals(1, items.get(0).getId()); // true

    }
}
