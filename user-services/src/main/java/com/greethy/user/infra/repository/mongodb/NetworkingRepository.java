package com.greethy.user.infra.repository.mongodb;

import com.greethy.user.core.domain.entity.Networking;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkingRepository extends ReactiveMongoRepository<Networking, String> {
}
