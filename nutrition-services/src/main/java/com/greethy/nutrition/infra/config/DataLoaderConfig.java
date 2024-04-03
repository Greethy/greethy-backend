package com.greethy.nutrition.infra.config;

import com.greethy.nutrition.core.domain.entity.evaluate.BmiEvaluate;
import com.greethy.nutrition.core.domain.entity.evaluate.BmrByAge;
import com.greethy.nutrition.core.domain.entity.evaluate.PalEvaluate;
import com.greethy.nutrition.core.domain.entity.evaluate.Range;
import com.greethy.nutrition.core.port.out.evaluate.bmi.DeleteBmiEvaluatePort;
import com.greethy.nutrition.core.port.out.evaluate.bmi.SaveBmiEvaluatePort;
import com.greethy.nutrition.core.port.out.evaluate.bmr.DeleteBmrByAgePort;
import com.greethy.nutrition.core.port.out.evaluate.bmr.SaveBmrByAgePort;
import com.greethy.nutrition.core.port.out.evaluate.pal.DeletePalEvaluatePort;
import com.greethy.nutrition.core.port.out.evaluate.pal.SavePalEvaluatePort;
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

    private final SaveBmiEvaluatePort saveBmiEvaluatePort;

    private final DeleteBmiEvaluatePort deleteBmiEvaluatePort;

    private final SaveBmrByAgePort saveBmrByAgePort;

    private final DeleteBmrByAgePort deleteBmrByAgePort;

    private final SavePalEvaluatePort savePalEvaluatePort;

    private final DeletePalEvaluatePort deletePalEvaluatePort;

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
                .then(deleteBmrByAgePort.deleteAll())
                .thenMany(Flux.just(bmrByAgesTable()))
                .flatMap(saveBmrByAgePort::saveAll)
                .then(deletePalEvaluatePort.deleteAll())
                .thenMany(Flux.just(palEvaluates()))
                .flatMap(savePalEvaluatePort::saveAll)
                .subscribe(palEvaluate -> log.info("Load PAL Evaluate data to MongoDB: {}", palEvaluate));
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

    private Set<PalEvaluate> palEvaluates() {
        Set<PalEvaluate> palEvaluates = new HashSet<>();
        palEvaluates.add(new PalEvaluate(new Range(0d, 0d), 0d,0d,0d));
        palEvaluates.add(new PalEvaluate(new Range(1d, 2d), 1.35d, 1.35d, 1.35d));
        palEvaluates.add(new PalEvaluate(new Range(3d, 5d), 1.45d, 1.45d, 1.45d));
        palEvaluates.add(new PalEvaluate(new Range(6d, 7d), 1.35d, 1.55d, 1.74d));
        palEvaluates.add(new PalEvaluate(new Range(8d, 9d), 1.40d, 1.6d, 1.8d));
        palEvaluates.add(new PalEvaluate(new Range(10d, 11d), 1.45d, 1.65d, 1.85d));
        palEvaluates.add(new PalEvaluate(new Range(12d, 14d), 1.5d, 1.7d, 1.9d));
        palEvaluates.add(new PalEvaluate(new Range(15d, 19d), 1.55d, 1.75d, 1.95d));
        palEvaluates.add(new PalEvaluate(new Range(20d, 29d), 1.5d, 1.75d, 2d));
        palEvaluates.add(new PalEvaluate(new Range(30d, 49d), 1.5d, 1.75d, 2d));
        palEvaluates.add(new PalEvaluate(new Range(50d, 69d), 1.5d, 1.75d, 2d));
        palEvaluates.add(new PalEvaluate(new Range(70d, Double.MAX_VALUE), 1.45d, 1.7d, 1.95d));
        return palEvaluates;
    }


}
