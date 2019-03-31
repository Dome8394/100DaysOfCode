package com.codingdays.spring.springdata.repositories.customer;

import com.codingdays.spring.springdata.entities.CustomerEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface CustomerEntityRepository extends MongoRepository<CustomerEntity, ObjectId> {

    List<CustomerEntity> findAllByFirstName(String firstName);

    CustomerEntity findByFirstName(String firstName);
}
