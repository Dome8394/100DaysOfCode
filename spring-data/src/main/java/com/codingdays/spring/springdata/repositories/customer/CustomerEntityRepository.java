package com.codingdays.spring.springdata.repositories.customer;

import com.codingdays.spring.springdata.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerEntityRepository extends CrudRepository<CustomerEntity, Long> {

    List<CustomerEntity> findAllByFirstName(String firstName);
}
