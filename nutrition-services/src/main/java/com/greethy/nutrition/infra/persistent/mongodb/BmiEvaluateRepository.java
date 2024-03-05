package com.greethy.nutrition.infra.persistent.mongodb;

import com.greethy.nutrition.core.domain.entity.evaluate.BmiEvaluate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BmiEvaluateRepository extends ReactiveMongoRepository<BmiEvaluate, String> {
}
