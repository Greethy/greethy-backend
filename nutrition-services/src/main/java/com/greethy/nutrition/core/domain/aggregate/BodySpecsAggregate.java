package com.greethy.nutrition.core.domain.aggregate;

import com.greethy.core.domain.event.UserBodySpecsAddedEvent;
import com.greethy.nutrition.core.domain.entity.evaluate.BmiEvaluate;
import com.greethy.nutrition.core.domain.entity.evaluate.BmrByAge;
import com.greethy.nutrition.core.domain.entity.specs.Bmi;
import com.greethy.nutrition.core.domain.entity.specs.Bmr;
import com.greethy.nutrition.core.domain.entity.specs.Gender;
import com.greethy.nutrition.core.domain.entity.specs.Pal;
import com.greethy.nutrition.core.event.BodySpecsCreatedEvent;
import com.greethy.nutrition.core.port.in.command.CreateBodySpecsCommand;
import com.greethy.nutrition.core.port.out.evaluate.bmi.FindBmiEvaluatePort;
import com.greethy.nutrition.core.port.out.evaluate.bmr.FindBmrByAgePort;
import com.greethy.nutrition.core.port.out.evaluate.pal.FindPalEvaluatePort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.Arrays;

@Getter
@Aggregate
@NoArgsConstructor
public class BodySpecsAggregate {

    @AggregateIdentifier
    private String id;

    private Integer age;

    private Double height;

    private Double weight;

    private String gender;

    private Bmi bmi;

    private Bmr bmr;

    private Pal pal;

    private Double tdee;

    @CommandHandler
    BodySpecsAggregate(CreateBodySpecsCommand command,
                       FindBmiEvaluatePort findBmiEvaluatePort,
                       FindBmrByAgePort findBmrByAgePort,
                       FindPalEvaluatePort findPalEvaluatePort) {
        var bmi = new Bmi();
        var bmr = new Bmr();
        var pal = new Pal();
        Double bmiIndex = (command.getWeight() / (command.getHeight() * command.getHeight()));
        Mono.just(Double.valueOf(new DecimalFormat("#.#").format(bmiIndex)))
                .doOnNext(bmi::setIndex)
                .flatMap(findBmiEvaluatePort::findByIndexInRange)
                .map(BmiEvaluate::getCategory)
                .doOnNext(bmi::setStatus)
                .then(Mono.just(command.getAge()))
                .flatMap(findBmrByAgePort::findByAgeGroup)
                .map(BmrByAge::getBmrPerKg)
                .doOnNext(bmrByAge -> {
                    bmr.setBmrPerKg(bmrByAge);
                    bmr.setBmrPerDay(bmr.getBmrPerKg() * command.getWeight());
                })
                .then(Mono.just(command.getAge()))
                .flatMap(findPalEvaluatePort::findByAgeGroup)
                .doOnNext(palEvaluate -> {
                    System.out.println(palEvaluate);
                    switch (command.getActivityType()) {
                        case "sedentary" -> pal.setValue(palEvaluate.getSedentary());
                        case "moderately" -> pal.setValue(palEvaluate.getModerately());
                        case "vigorous" -> pal.setValue(palEvaluate.getVigorous());
                        default -> pal.setValue(1.6d);
                    }
                    pal.setActivityType(command.getActivityType());
                })
                .block();
        AggregateLifecycle.apply(BodySpecsCreatedEvent.builder()
                        .bodySpecsId(command.getBodySpecsId())
                        .age(command.getAge())
                        .height(command.getHeight())
                        .weight(command.getWeight())
                        .gender(convertGender(command.getGender()))
                        .bmi(bmi).bmr(bmr).pal(pal)
                        .tdee(bmr.getBmrPerDay() * pal.getValue())
                        .build())
                .andThenApply(() -> UserBodySpecsAddedEvent.builder()
                        .userId(command.getUserId())
                        .bodySpecsId(command.getBodySpecsId())
                        .build());
    }

    private String convertGender(Integer genderValue) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.getValue().equals(genderValue))
                .findFirst().orElse(Gender.MALE)
                .getName();
    }

    @EventSourcingHandler
    void on(BodySpecsCreatedEvent event) {
        this.id = event.getBodySpecsId();
        this.age = event.getAge();
        this.height = event.getHeight();
        this.weight = event.getHeight();
        this.gender = event.getGender();
        this.bmi = event.getBmi();
        this.bmr = event.getBmr();
        this.pal = event.getPal();
        this.tdee = event.getTdee();
    }

}
