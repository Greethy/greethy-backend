package com.greethy.nutritioncommon.entity;

import com.greethy.common.domain.entity.BaseEntity;
import com.greethy.nutritioncommon.entity.value.MealMenu;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "menus")
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {

    @Id
    private String id;

    private String name;

    private List<String> labels;

    private LocalDate useFor;

    @Field(name = "menu_type")
    private List<String> menuType;

    private List<MealMenu> meals;

    @Field(name = "owned_by")
    private String ownedBy;

}
