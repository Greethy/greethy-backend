package com.greethy.nutritioncommon.entity.value;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
public class MenuForDate {

    private LocalDate date;

    @Field(name = "menu_id")
    private String menuId;

}
