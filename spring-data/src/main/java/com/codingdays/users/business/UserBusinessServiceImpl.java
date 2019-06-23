package com.codingdays.users.business;

import com.codingdays.users.entities.User;
import com.codingdays.users.repositories.users.UserRepository;
import com.codingdays.users.services.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author dominik on 08.03.19
 * @project spring-data
 */
@Component
public class UserBusinessServiceImpl implements UserBusinessService {

    private final MongoTemplate mongoTemplate;

    public UserBusinessServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<User> getUsers() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public User getUserById(String Id) {
        User user;
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(Id));

        user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    @Override
    public String saveUser(User user) {
        mongoTemplate.save(user);
        return user.toString();
    }
}
