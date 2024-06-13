package com.greethy.nutritioncommon.entity;

import com.greethy.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "menus")
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {

    @Id
    private String id;


    private String name;
}
