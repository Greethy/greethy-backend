package com.greethy.nutritioncommon.entity;

import com.greethy.common.domain.entity.BaseEntity;
import com.greethy.nutritioncommon.entity.value.MenuForDate;
import com.greethy.nutritioncommon.entity.value.Owner;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "eating_plan")
public class EatingPlan extends BaseEntity {

    @Id
    private String id;

    private String name;

    private String bio;

    private Owner owner;

    private Integer calories;

    @Field(name = "participants_ids")
    private List<String> participantIds;

    private List<MenuForDate> plan;

}
