package com.greethy.user.infra.repository.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.greethy.user.core.domain.entity.Networking;

@Repository
public interface NetworkingRepository extends ReactiveMongoRepository<Networking, String> {}
