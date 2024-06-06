package com.greethy.nutritioncommand;

import com.greethy.nutritioncommon.entity.BmiEvaluate;
import com.greethy.nutritioncommon.entity.BmrByAge;
import com.greethy.nutritioncommon.entity.PalEvaluate;
import com.greethy.nutritioncommon.entity.value.Range;
import com.greethy.nutritioncommon.repository.BmiEvaluateRepository;
import com.greethy.nutritioncommon.repository.BmrByAgeRepository;
import com.greethy.nutritioncommon.repository.PalEvaluateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication(
        scanBasePackages = {
                "com.greethy.common",
                "com.greethy.nutritioncommon",
                "com.greethy.nutritioncommand"
        })
public class NutritionCommandApplication implements CommandLineRunner {

    private final BmiEvaluateRepository bmiEvaluateRepository;

    private final BmrByAgeRepository bmrByAgeRepository;

    private final PalEvaluateRepository palEvaluateRepository;

    public static void main(String[] args) {
        SpringApplication.run(NutritionCommandApplication.class);
    }

    @Override
    public void run(String... args) {
        Mono.when(
                bmiEvaluateRepository.deleteAll()
                        .doOnSuccess(unused -> log.info("Deleted BMI Evaluate collections")),
                bmrByAgeRepository.deleteAll()
                        .doOnSuccess(unused -> log.info("Deleted BMR by Age collections")),
                palEvaluateRepository.deleteAll()
                        .doOnSuccess(unused -> log.info("Deleted Pal Evaluate collections"))
        ).then(Mono.when(
                Flux.fromIterable(bmiEvaluates())
                        .flatMap(bmiEvaluateRepository::save)
                        .doOnNext(bmiEvaluate -> log.info("BMI Evaluate: {} saved to MongoDB.", bmiEvaluate))
                        .then(),
                Flux.fromIterable(bmrByAgesTable())
                        .flatMap(bmrByAgeRepository::save)
                        .doOnNext(bmrByAge -> log.info("BMR by Age: {} saved to MongoDB.", bmrByAge))
                        .then(),
                Flux.fromIterable(palEvaluates())
                        .flatMap(palEvaluateRepository::save)
                        .doOnNext(palEvaluate -> log.info("PAL Evaluate: {} saved to MongoDB.", palEvaluate))
                        .then())
        ).subscribe();
    }

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
        palEvaluates.add(new PalEvaluate(new Range(0d, 0d), 0d, 0d, 0d));
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