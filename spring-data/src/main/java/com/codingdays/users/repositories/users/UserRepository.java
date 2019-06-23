package com.codingdays.users.repositories.customer;

import com.codingdays.users.entities.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerEntityRepository extends MongoRepository<CustomerEntity, String> {
}
