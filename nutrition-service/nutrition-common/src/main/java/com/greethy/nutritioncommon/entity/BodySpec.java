package com.greethy.nutritioncommon.entity;

import com.greethy.common.domain.entity.BaseEntity;
import com.greethy.nutritioncommon.entity.value.Bmi;
import com.greethy.nutritioncommon.entity.value.Bmr;
import com.greethy.nutritioncommon.entity.value.Pal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Data
@NoArgsConstructor
@Document(collection = "body_specs")
@EqualsAndHashCode(callSuper = true)
public class BodySpec extends BaseEntity {

    @MongoId
    private String id;

    private Double height;

    private Double weight;

    private Integer age;

    private Double tdee;

    private Bmi bmi;

    private Pal pal;

    private Bmr bmr;

    private String goal;

}
