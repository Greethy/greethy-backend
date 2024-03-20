package com.greethy.nutrition.infra.config;

import com.greethy.nutrition.core.domain.entity.evaluate.BmiEvaluate;
import com.greethy.nutrition.core.domain.entity.evaluate.BmrByAge;
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
 * The {@code DataLoaderConfig} class is responsible for initializing data upon application startup.
 * This class listens for the ApplicationReadyEvent and triggers the data initialization process.
 *
 * @author Kien N.Thanh
 * */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoaderConfig {

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
                .doOnNext(bmiEvaluate -> log.info("Load BMI Evaluate data to MongoDB: {}", bmiEvaluate))
                .thenMany(Flux.just(bmrByAgesTable()))
                .subscribe(bmrByAges -> log.info("Load BMI Evaluate data to MongoDB: {}", bmrByAges));
    }

    /**
     * Creates a set of BMI evaluations with predefined ranges.
     * @return A set of BMI evaluations.
     */
    private Set<BmiEvaluate> bmiEvaluates() {
        Set<BmiEvaluate> bmiEvaluates = new HashSet<>();
        bmiEvaluates.add(new BmiEvaluate("Underweight III", new Range(0d, 16d)));
        bmiEvaluates.add(new BmiEvaluate("Underweight II", new Range(16d, 16.9d)));
        bmiEvaluates.add(new BmiEvaluate("Underweight I", new Range(17d, 18.4d)));
        bmiEvaluates.add(new BmiEvaluate("Normal", new Range(18.5d, 22.9d)));
        bmiEvaluates.add(new BmiEvaluate("Pre-obese", new Range(23d, 24.9d)));
        bmiEvaluates.add(new BmiEvaluate("Obesity I", new Range(25d, 29.9d)));
        bmiEvaluates.add(new BmiEvaluate("Obesity II", new Range(30d, Double.MAX_VALUE)));
        return bmiEvaluates;
    }

    /**
     * Creates a set of BMR by ages table.
     * @return A set of BMR by ages.
     *
     * */
    private Set<BmrByAge> bmrByAgesTable() {
        Set<BmrByAge> bmrByAges = new HashSet<>();
        bmrByAges.add(new BmrByAge(new Range(0d, 2d), 61d));
        bmrByAges.add(new BmrByAge(new Range(3d, 5d), 54.8d));
        bmrByAges.add(new BmrByAge(new Range(6d, 7d), 44.3d));
        bmrByAges.add(new BmrByAge(new Range(8d, 9d), 40.8d));
        bmrByAges.add(new BmrByAge(new Range(10d, 11d), 37.4d));
        bmrByAges.add(new BmrByAge(new Range(12d, 14d), 31d));
        bmrByAges.add(new BmrByAge(new Range(15d, 19d), 27.8d));
        bmrByAges.add(new BmrByAge(new Range(20d, 29d), 24d));
        bmrByAges.add(new BmrByAge(new Range(30d, 39d), 22.3d));
        bmrByAges.add(new BmrByAge(new Range(50d, Double.MAX_VALUE), 21.5d));
        return bmrByAges;
    }

}
