package com.greethy.usercommon.repository.mongodb;

import com.greethy.usercommon.entity.Networking;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkingRepository extends ReactiveMongoRepository<Networking, String> {
}
