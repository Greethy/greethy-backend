package com.greethy.user.common.repository;

import com.greethy.user.common.entity.Networking;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkingRepository extends ReactiveMongoRepository<Networking, String> {
}
