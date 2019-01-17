package com.codingdays.spring.springdata;

import com.codingdays.spring.springdata.controllers.customers.CustomerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataApplicationTests {


	@Autowired
	public CustomerController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

}

