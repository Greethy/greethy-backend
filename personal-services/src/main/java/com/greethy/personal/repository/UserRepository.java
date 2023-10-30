package com.greethy.personal.repository;

import com.greethy.personal.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ThanhKien
 * */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
