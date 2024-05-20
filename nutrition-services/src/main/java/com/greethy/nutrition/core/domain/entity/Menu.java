package com.greethy.nutrition.core.domain.entity;

import com.greethy.core.domain.entity.BaseEntity;
import com.greethy.nutrition.core.port.in.command.CreateDefaultMenuCommand;
import com.greethy.nutrition.core.port.in.command.CreateMenuCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;


@Data
@Aggregate
@NoArgsConstructor
@Document(collection = "menus")
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {

    @Id
    @AggregateIdentifier
    private String id;

    private String name;

    private List<String> labels;

    private String category;

    @Field(name = "total_calories")
    private Integer totalCalories;

    private Set<Food> foods;

    @CommandHandler
    Menu(CreateMenuCommand command) {

    }

    @CommandHandler
    void handle(CreateDefaultMenuCommand command) {

    }

}
