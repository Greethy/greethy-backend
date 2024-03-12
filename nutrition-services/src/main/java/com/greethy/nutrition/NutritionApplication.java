package com.greethy.nutrition;

import com.greethy.nutrition.core.domain.entity.evaluate.BmiEvaluate;
import com.greethy.nutrition.core.domain.entity.evaluate.Range;
import com.greethy.nutrition.core.port.out.evaluate.DeleteBmiEvaluatePort;
import com.greethy.nutrition.core.port.out.evaluate.SaveBmiEvaluatePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.Set;

/**
 * The main entry point for the Nutrition Application. This Spring Boot application initializes and manages
 * the backend for a nutrition-related system. It populates initial data into the database upon application
 * startup.
 *
 * @author Kien N.Thanh
 */
@Slf4j
@RequiredArgsConstructor
@SpringBootApplication(scanBasePackages = {
        "com.greethy.nutrition",
        "com.greethy.core",
        "com.greethy.mapper"
})
public class NutritionApplication {

    private final DeleteBmiEvaluatePort deleteBmiEvaluatePort;

    private final SaveBmiEvaluatePort saveBmiEvaluatePort;

    /**
     * Main method to start the Nutrition Application.
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(NutritionApplication.class, args);
    }

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
        bmiEvaluates.add(new BmiEvaluate("Normal", new Range(18.5d, 22.9d)));
        bmiEvaluates.add(new BmiEvaluate("Pre-obese", new Range(23d, 24.9d)));
        bmiEvaluates.add(new BmiEvaluate("Obesity I", new Range(25d, 29.9d)));
        bmiEvaluates.add(new BmiEvaluate("Obesity II", new Range(30d, Double.MAX_VALUE)));
        return bmiEvaluates;
    }
}
