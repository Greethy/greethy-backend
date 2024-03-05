package com.greethy.nutrition.core.domain.aggregate;

import com.greethy.nutrition.core.domain.entity.body_specs.Bmi;
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

@Getter
@Aggregate
@NoArgsConstructor
public class BodySpecsAggregate {

    @AggregateIdentifier
    private String id;

    private Integer age;

    private Double height;

    private Double weight;

    private Bmi bmi;

    @CommandHandler
    BodySpecsAggregate(CreateBodySpecsCommand command, FindBmiEvaluatePort findBmiEvaluatePort) {
        Double bmiIndex = command.getWeight() / (command.getHeight() * command.getHeight());

        Bmi bmi = findBmiEvaluatePort.findAll()
                .filter(bmiEvaluate -> bmiEvaluate.getRange().from() < bmiIndex
                                        && bmiEvaluate.getRange().to() > bmiIndex
                ).map(bmiEvaluate ->
                        Bmi.builder()
                                .status(bmiEvaluate.getCategory())
                                .index(bmiIndex)
                                .build()
                )
                .blockFirst();
        var event = BodySpecsCreatedEvent.builder()
                .bodySpecsId(command.getBodySpecsId())
                .age(command.getAge())
                .height(command.getHeight())
                .weight(command.getWeight())
                .bmi(bmi)
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    void on(BodySpecsCreatedEvent event) {
        this.id = event.getBodySpecsId();
        this.age = event.getAge();
        this.height = event.getHeight();
        this.weight = event.getHeight();
        this.bmi = event.getBmi();
    }

}
