package com.greethy.nutrition.infra.repository.mongodb;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.greethy.nutrition.core.domain.value.BmrByAge;

import reactor.core.publisher.Mono;

@Repository
public interface BmrByAgeRepository extends ReactiveMongoRepository<BmrByAge, String> {
    @Query("""
		{
			'$and' : [
				{'age_group.from' : {$lte: ?0 }},
				{'age_group.to' : {$gte: ?0 }}
			]
		}
	""")
    Mono<BmrByAge> findByAgeGroup(int age);
}