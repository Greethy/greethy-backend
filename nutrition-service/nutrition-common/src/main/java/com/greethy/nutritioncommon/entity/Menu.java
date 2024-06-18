package com.greethy.nutritioncommon.entity;

import com.greethy.common.domain.entity.BaseEntity;
import com.greethy.nutritioncommon.entity.value.MealMenu;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "menus")
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {

    @Id
    private String id;

    private String name;

    private List<String> labels;

    private List<String> menuType;

    private List<MealMenu> meals;

}
