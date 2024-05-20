package com.greethy.nutrition.core.domain.service;

import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

import com.greethy.nutrition.core.domain.value.*;
import com.greethy.nutrition.core.domain.value.enums.ActivityLevel;
import com.greethy.nutrition.core.port.out.BmiEvaluatePort;
import com.greethy.nutrition.core.port.out.BmrByAgePort;
import com.greethy.nutrition.core.port.out.PalEvaluatePort;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BodySpecsCalculator {

    private final BmiEvaluatePort bmiEvaluatePort;

    private final BmrByAgePort bmrByAgePort;

    private final PalEvaluatePort palEvaluatePort;

    public FitnessIndexes calculate(double height, double weight, int age, ActivityLevel activityLevel) {
        var fitnessIndexes = new FitnessIndexes();
        var bmi = fitnessIndexes.getBmi();
        var bmr = fitnessIndexes.getBmr();
        var pal = fitnessIndexes.getPal();
        var bmiIndex = (weight / (height * height));
        Mono.just(Double.valueOf(new DecimalFormat("#.#").format(bmiIndex)))
                .doOnNext(bmi::setIndex)
                .flatMap(bmiEvaluatePort::findByIndexInRange)
                .map(BmiEvaluate::getCategory)
                .doOnNext(bmi::setStatus)
                .then(Mono.just(age))
                .flatMap(bmrByAgePort::findByAgeGroup)
                .map(BmrByAge::getBmrPerKg)
                .doOnNext(bmrByAge -> {
                    bmr.setBmrPerKg(bmrByAge);
                    bmr.setBmrPerDay(bmr.getBmrPerKg() * weight);
                })
                .then(Mono.just(age))
                .flatMap(palEvaluatePort::findByAgeGroup)
                .doOnNext(palEvaluate -> {
                    switch (activityLevel) {
                        case SEDENTARY -> pal.setValue(palEvaluate.getSedentary());
                        case MODERATELY -> pal.setValue(palEvaluate.getModerately());
                        case VIGOROUS -> pal.setValue(palEvaluate.getVigorous());
                        default -> pal.setValue(1.6d);
                    }
                    pal.setActivityLevel(activityLevel.getName());
                })
                .block();

        return fitnessIndexes;
    }

    @Data
    public static class FitnessIndexes {

        private Bmi bmi;

        private Bmr bmr;

        private Pal pal;

        public FitnessIndexes() {
            this.bmi = new Bmi();
            this.bmr = new Bmr();
            this.pal = new Pal();
        }
    }
}
