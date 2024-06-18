package com.greethy.nutritioncommon.entity;

import com.greethy.common.domain.entity.BaseEntity;
import com.greethy.nutritioncommon.entity.value.Nutrient;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Document(collection = "ingredients")
public class Ingredient extends BaseEntity {

    @Id
    private String id;

    private String name;

    private String code;

    private Integer calories;

    private List<Nutrient> nutrients;

}
