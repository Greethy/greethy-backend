package com.greethy.nutritioncommon.entity;

import com.greethy.common.domain.entity.BaseEntity;
import com.greethy.nutritioncommon.entity.value.MenuForDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Document
@EqualsAndHashCode(callSuper = true)
public class EatingSchedule extends BaseEntity {

    @MongoId
    private String id;

    @Field(name = "eating_plan_id")
    private String eatingPlanId;

    private List<MenuForDate> schedule;

}
