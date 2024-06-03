package com.greethy.nutritioncommon.repository;

import com.greethy.nutritioncommon.entity.BmiEvaluate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BmiEvaluateRepository extends ReactiveMongoRepository<BmiEvaluate, String> {

    @Query("""
		{
			'$and' : [
				{'range.from' : {$lte: ?0 }},
				{'range.to' : {$gte: ?0 }}
			]
		}
	""")
    Mono<BmiEvaluate> findByIndexInRange(Double bmiIndex);
}
