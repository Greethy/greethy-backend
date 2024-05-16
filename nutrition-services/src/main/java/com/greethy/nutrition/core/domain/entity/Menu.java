package com.greethy.nutrition.core.domain.entity;

import com.greethy.core.domain.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;

@Data
@Aggregate
@NoArgsConstructor
@Document(collection = "menus")
public class Menu extends BaseEntity {

    @MongoId
    @AggregateIdentifier
    private String id;

    private List<String> labels;

    @Field(name = "total_calories")
    private Integer totalCalories;

    private List<Food> foods;

    @CommandHandler
    Menu(Object s) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu menu)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), menu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }
}
