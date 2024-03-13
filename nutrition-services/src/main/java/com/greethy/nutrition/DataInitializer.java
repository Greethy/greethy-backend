package com.greethy.nutrition;

import com.greethy.nutrition.core.domain.entity.evaluate.BmiEvaluate;
import com.greethy.nutrition.core.domain.entity.evaluate.Range;
import com.greethy.nutrition.core.port.out.evaluate.DeleteBmiEvaluatePort;
import com.greethy.nutrition.core.port.out.evaluate.SaveBmiEvaluatePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * The {@code DataInitializer} class is responsible for initializing data upon application startup.
 * This class listens for the ApplicationReadyEvent and triggers the data initialization process.
 *
 * @author Kien N.Thanh
 * */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final DeleteBmiEvaluatePort deleteBmiEvaluatePort;

    private final SaveBmiEvaluatePort saveBmiEvaluatePort;

    /**
     * Initializes data upon application startup. Deletes existing
     * BMI evaluations and saves a new set of BMI evaluations.
     */
    @EventListener(value = ApplicationReadyEvent.class)
    void init() {
        log.info("Start data initialization....");
        deleteBmiEvaluatePort.deleteAll()
                .thenMany(Flux.just(bmiEvaluates()))
                .flatMap(saveBmiEvaluatePort::saveAll)
                .subscribe(bmiEvaluate -> log.info("Load BMI Evaluate data to MongoDB: {}", bmiEvaluate));
    }

    /**
     * Creates a list of BMI evaluations with predefined ranges.
     * @return A list of BMI evaluations.
     */
    private Set<BmiEvaluate> bmiEvaluates() {
        Set<BmiEvaluate> bmiEvaluates = new HashSet<>();
        bmiEvaluates.add(new BmiEvaluate("Underweight III", new Range(0d, 16d)));
        bmiEvaluates.add(new BmiEvaluate("Underweight II", new Range(16d, 16.9d)));
        bmiEvaluates.add(new BmiEvaluate("Underweight I", new Range(17d, 18.4d)));
        bmiEvaluates.add(new BmiEvaluate("Normal", new Range(18.5d, 24.9d)));
        bmiEvaluates.add(new BmiEvaluate("Pre-obese", new Range(25d, 29.9d)));
        bmiEvaluates.add(new BmiEvaluate("Obesity I", new Range(30d, 34.9d)));
        bmiEvaluates.add(new BmiEvaluate("Obesity II", new Range(35d, 39.9d)));
        bmiEvaluates.add(new BmiEvaluate("Obesity III", new Range(40d, Double.MAX_VALUE)));
        return bmiEvaluates;
    }

}
