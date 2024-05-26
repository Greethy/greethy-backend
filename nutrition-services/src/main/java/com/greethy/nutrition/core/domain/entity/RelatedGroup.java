package com.greethy.nutrition.core.domain.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Set;

@Data
@Document(collection = "related_group")
public class RelatedGroup {

    @MongoId(FieldType.STRING)
    private String id;

    private String name;

    @Indexed
    @Field(name = "nutrition_type")
    private String NutritionType;

    @Indexed
    private String meal;

    @Field(name = "cereal_ids")
    private Set<String> cerealIds;

    @Field(name = "vegetable_ids")
    private Set<String> vegetableIds;

    @Field(name = "protein_ids")
    private Set<String> proteinIds;

    @Field(name = "soup_ids")
    private Set<String> soupIds;

    @Field(name = "desert_ids")
    private Set<String> desertIds;

}
