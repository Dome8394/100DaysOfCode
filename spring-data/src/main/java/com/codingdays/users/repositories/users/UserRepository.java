package com.codingdays.users.repositories.users;

import com.codingdays.users.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
