package com.greethy.nutrition.repository.mongodb;

import com.greethy.nutrition.infra.persistent.mongodb.BmiEvaluateRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataMongoTest
public class BmiEvaluateRepositoryTests {

    @Autowired
    private BmiEvaluateRepository bmiEvaluateRepository;

    @TestFactory
    List<DynamicTest> testMongoFindBmiEvaluate() {
        return List.of(
                DynamicTest.dynamicTest("find all", () -> bmiEvaluateRepository.findAll()
                  .as(StepVerifier::create)
                  .consumeNextWith(bmiEvaluate -> assertThat(bmiEvaluate).isNotNull())
                  .verifyComplete()
                ),
                DynamicTest.dynamicTest("find by range", () -> bmiEvaluateRepository.findByIndexInRange(22.5d)
                        .as(StepVerifier::create)
                        .consumeNextWith(bmiEvaluate -> assertThat(bmiEvaluate.getCategory()).isEqualTo("Normal"))
                        .verifyComplete()
                )
        );
    }

}
