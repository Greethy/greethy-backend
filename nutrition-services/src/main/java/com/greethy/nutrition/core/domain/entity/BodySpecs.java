package com.greethy.nutrition.core.domain.entity;

import java.time.LocalDate;
import java.util.Arrays;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.greethy.core.domain.event.UserBodySpecsAddedEvent;
import com.greethy.core.domain.event.UserBodySpecsDeletedEvent;
import com.greethy.nutrition.core.domain.service.BodySpecsCalculator;
import com.greethy.nutrition.core.domain.value.Bmi;
import com.greethy.nutrition.core.domain.value.Bmr;
import com.greethy.nutrition.core.domain.value.Gender;
import com.greethy.nutrition.core.domain.value.Pal;
import com.greethy.nutrition.core.event.BodySpecsCreatedEvent;
import com.greethy.nutrition.core.event.BodySpecsDeletedEvent;
import com.greethy.nutrition.core.event.BodySpecsUpdatedEvent;
import com.greethy.nutrition.core.port.in.command.CreateBodySpecsCommand;
import com.greethy.nutrition.core.port.in.command.DeleteBodySpecsCommand;
import com.greethy.nutrition.core.port.in.command.UpdateBodySpecsCommand;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Aggregate
@NoArgsConstructor
@Document(collection = "body_specs")
public class BodySpecs {

    @Id
    @AggregateIdentifier
    private String id;

    private Integer age;

    private Double height;

    private Double weight;

    private String gender;

    private Double tdee;

    private Bmi bmi;

    private Pal pal;

    private Bmr bmr;

    private String goal;

    @Field(name = "created_at")
    private LocalDate createAt;

    @CommandHandler
    BodySpecs(CreateBodySpecsCommand command, BodySpecsCalculator calculator, ModelMapper mapper) {
        var indexes = calculator.calculate(
                command.getHeight(), command.getWeight(), command.getAge(), command.getActivityType());
        var event = mapper.map(command, BodySpecsCreatedEvent.class);
        event.setGender(convertGender(command.getGender()));
        event.setBmi(indexes.getBmi());
        event.setBmr(indexes.getBmr());
        event.setPal(indexes.getPal());
        event.setTdee(indexes.getBmr().getBmrPerDay() * indexes.getPal().getValue());

        AggregateLifecycle.apply(event).andThenApply(() -> UserBodySpecsAddedEvent.builder()
                .userId(command.getUserId())
                .bodySpecsId(command.getBodySpecsId())
                .goal(command.getGoal())
                .build());
    }

    private String convertGender(Integer genderValue) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.getValue().equals(genderValue))
                .findFirst()
                .orElse(Gender.MALE)
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
        this.goal = event.getGoal();
    }

    @CommandHandler
    void handle(UpdateBodySpecsCommand command, BodySpecsCalculator calculator, ModelMapper mapper) {
        var indexes = calculator.calculate(
                command.getHeight(), command.getWeight(), command.getAge(), command.getActivityType());
        var event = mapper.map(command, BodySpecsCreatedEvent.class);
        event.setGender(convertGender(command.getGender()));
        event.setBmi(indexes.getBmi());
        event.setBmr(indexes.getBmr());
        event.setPal(indexes.getPal());
        event.setTdee(indexes.getBmr().getBmrPerDay() * indexes.getPal().getValue());
        AggregateLifecycle.apply(event);
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
    void handle(DeleteBodySpecsCommand command, ModelMapper mapper) {
        var event = mapper.map(command, BodySpecsDeletedEvent.class);
        AggregateLifecycle.apply(event).andThenApply(() -> mapper.map(event, UserBodySpecsDeletedEvent.class));
    }

    @EventSourcingHandler
    void on(BodySpecsDeletedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
