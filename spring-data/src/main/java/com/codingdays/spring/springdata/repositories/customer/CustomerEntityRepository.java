package com.codingdays.spring.springdata.repositories.customer;

import com.codingdays.spring.springdata.entities.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerEntityRepository extends MongoRepository<CustomerEntity, Long> {

    List<CustomerEntity> findAllByFirstName(String firstName);
}
