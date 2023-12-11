package com.greethy.personal.infrastructure.mongodb;

import com.greethy.personal.domain.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ThanhKien
 * */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
