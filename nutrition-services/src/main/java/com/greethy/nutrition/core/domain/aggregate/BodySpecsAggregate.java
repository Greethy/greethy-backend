package com.greethy.nutrition.core.domain.aggregate;

import com.greethy.core.domain.event.UserBodySpecsAddedEvent;
import com.greethy.nutrition.core.domain.entity.specs.Bmi;
import com.greethy.nutrition.core.domain.entity.specs.Bmr;
import com.greethy.nutrition.core.domain.entity.specs.Gender;
import com.greethy.nutrition.core.domain.entity.specs.Pal;
import com.greethy.nutrition.core.domain.service.BodySpecsCalculator;
import com.greethy.nutrition.core.event.BodySpecsCreatedEvent;
import com.greethy.nutrition.core.event.BodySpecsDeletedEvent;
import com.greethy.nutrition.core.event.BodySpecsUpdatedEvent;
import com.greethy.nutrition.core.port.in.command.CreateBodySpecsCommand;
import com.greethy.nutrition.core.port.in.command.DeleteBodySpecsCommand;
import com.greethy.nutrition.core.port.in.command.UpdateBodySpecsCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

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
                       BodySpecsCalculator calculator) {
        var indexes = calculator.calculate(command.getHeight(), command.getWeight(),
                command.getAge(), command.getActivityType());
        AggregateLifecycle.apply(BodySpecsCreatedEvent.builder()
                        .bodySpecsId(command.getBodySpecsId())
                        .age(command.getAge())
                        .height(command.getHeight())
                        .weight(command.getWeight())
                        .gender(convertGender(command.getGender()))
                        .bmi(indexes.getBmi()).bmr(indexes.getBmr()).pal(indexes.getPal())
                        .tdee(indexes.getBmr().getBmrPerDay() * indexes.getPal().getValue())
                        .build()
                ).andThenApply(() -> UserBodySpecsAddedEvent.builder()
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

    @CommandHandler
    void handle(UpdateBodySpecsCommand command, BodySpecsCalculator calculator) {
        var indexes = calculator.calculate(command.getHeight(), command.getWeight(),
                command.getAge(), command.getActivityType());
        AggregateLifecycle.apply(BodySpecsUpdatedEvent.builder()
                .bodySpecsId(command.getBodySpecsId())
                .age(command.getAge())
                .height(command.getHeight())
                .weight(command.getWeight())
                .gender(convertGender(command.getGender()))
                .bmi(indexes.getBmi()).bmr(indexes.getBmr()).pal(indexes.getPal())
                .tdee(indexes.getBmr().getBmrPerDay() * indexes.getPal().getValue())
                .build()
        );
    }

    @EventSourcingHandler
    void on(BodySpecsUpdatedEvent event) {
        this.height = event.getHeight();
        this.weight = event.getHeight();
        this.gender = event.getGender();
        this.age = event.getAge();
        this.bmi = event.getBmi();
        this.bmr = event.getBmr();
        this.pal = event.getPal();
        this.tdee = event.getTdee();
    }

    @CommandHandler
    void handle(DeleteBodySpecsCommand command) {
        AggregateLifecycle.apply(BodySpecsDeletedEvent.builder()
                .bodySpecsId(command.getBodySpecsId())
                .build());
    }

    @EventSourcingHandler
    void on(BodySpecsDeletedEvent event) {
        AggregateLifecycle.markDeleted();
    }

}
