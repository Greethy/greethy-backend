package com.greethy.nutritioncommon.repository;

import com.greethy.nutritioncommon.entity.BodySpec;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodySpecRepository extends ReactiveMongoRepository<BodySpec, String> {
}
