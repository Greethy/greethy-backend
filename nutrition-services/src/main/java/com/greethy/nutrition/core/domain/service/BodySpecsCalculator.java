package com.greethy.nutrition.core.domain.service;

import com.greethy.nutrition.core.domain.value.BmiEvaluate;
import com.greethy.nutrition.core.domain.value.BmrByAge;
import com.greethy.nutrition.core.domain.value.FitnessIndexes;
import com.greethy.nutrition.core.port.out.read.FindBmiEvaluatePort;
import com.greethy.nutrition.core.port.out.read.FindBmrByAgePort;
import com.greethy.nutrition.core.port.out.read.FindPalEvaluatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;

@Component
@RequiredArgsConstructor
public class BodySpecsCalculator {

    private final FindBmiEvaluatePort findBmiEvaluatePort;

    private final FindBmrByAgePort findBmrByAgePort;

    private final FindPalEvaluatePort findPalEvaluatePort;

    public FitnessIndexes calculate(double height, double weight, int age, String activityLevel) {
        var fitnessIndexes = new FitnessIndexes();
        var bmi = fitnessIndexes.getBmi();
        var bmr = fitnessIndexes.getBmr();
        var pal = fitnessIndexes.getPal();
        var bmiIndex = (weight / (height * height));
        Mono.just(Double.valueOf(new DecimalFormat("#.#").format(bmiIndex)))
                .doOnNext(bmi::setIndex)
                .flatMap(findBmiEvaluatePort::findByIndexInRange)
                .map(BmiEvaluate::getCategory)
                .doOnNext(bmi::setStatus)
                .then(Mono.just(age))
                .flatMap(findBmrByAgePort::findByAgeGroup)
                .map(BmrByAge::getBmrPerKg)
                .doOnNext(bmrByAge -> {
                    bmr.setBmrPerKg(bmrByAge);
                    bmr.setBmrPerDay(bmr.getBmrPerKg() * weight);
                })
                .then(Mono.just(age))
                .flatMap(findPalEvaluatePort::findByAgeGroup)
                .doOnNext(palEvaluate -> {
                    switch (activityLevel) {
                        case "sedentary" -> pal.setValue(palEvaluate.getSedentary());
                        case "moderately" -> pal.setValue(palEvaluate.getModerately());
                        case "vigorous" -> pal.setValue(palEvaluate.getVigorous());
                        default -> pal.setValue(1.6d);
                    }
                    pal.setActivityType(activityLevel);
                })
                .block();
        return fitnessIndexes;
    }

}
