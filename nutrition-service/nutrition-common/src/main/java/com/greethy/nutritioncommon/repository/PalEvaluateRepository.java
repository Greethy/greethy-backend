package com.greethy.nutritioncommon.repository;

import com.greethy.nutritioncommon.entity.PalEvaluate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PalEvaluateRepository extends ReactiveMongoRepository<PalEvaluate, String> {

    @Query("""
		{
			'$and' : [
				{'age_group.from' : {$lte: ?0 }},
				{'age_group.to' : {$gte: ?0 }}
			]
		}
	""")
    Mono<PalEvaluate> findByAgeGroup(int age);
}
