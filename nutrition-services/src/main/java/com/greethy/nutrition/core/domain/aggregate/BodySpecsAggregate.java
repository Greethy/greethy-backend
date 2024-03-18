package com.greethy.nutrition.core.domain.aggregate;

import com.greethy.core.domain.event.UserBodySpecsAddedEvent;
import com.greethy.nutrition.core.domain.entity.evaluate.BmiEvaluate;
import com.greethy.nutrition.core.domain.entity.specs.Bmi;
import com.greethy.nutrition.core.domain.entity.specs.Bmr;
import com.greethy.nutrition.core.domain.entity.specs.Gender;
import com.greethy.nutrition.core.domain.entity.specs.Pal;
import com.greethy.nutrition.core.event.BodySpecsCreatedEvent;
import com.greethy.nutrition.core.port.in.command.CreateBodySpecsCommand;
import com.greethy.nutrition.core.port.out.evaluate.FindBmiEvaluatePort;
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

    private Pal pal;

    private Bmr bmr;

    @CommandHandler
    BodySpecsAggregate(CreateBodySpecsCommand command,
                       FindBmiEvaluatePort findBmiEvaluatePort) {
        var bmi = new Bmi();
        Double bmiIndex = (command.getWeight() / (command.getHeight() * command.getHeight()));
        Mono.just(Double.valueOf(new DecimalFormat("#.#").format(bmiIndex)))
                .doOnNext(bmi::setIndex)
                .flatMap(findBmiEvaluatePort::findByIndexInRange)
                .map(BmiEvaluate::getCategory)
                .doOnNext(bmi::setStatus)
                .block();

        var bodySpecsCreatedEvent = BodySpecsCreatedEvent.builder()
                .bodySpecsId(command.getBodySpecsId())
                .age(command.getAge())
                .height(command.getHeight())
                .weight(command.getWeight())
                .gender(convertGender(command.getGender()))
                .bmi(bmi)
                .build();
        var userBodySpecsAddedEvent = UserBodySpecsAddedEvent.builder()
                .userId(command.getUserId())
                .bodySpecsId(command.getBodySpecsId())
                .build();
        AggregateLifecycle.apply(bodySpecsCreatedEvent)
                .andThenApply(() -> userBodySpecsAddedEvent);
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
    }

}
