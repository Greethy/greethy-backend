package com.greethy.nutrition.infra.persistent.mongodb;

import com.greethy.nutrition.core.domain.entity.evaluate.PalEvaluate;
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
