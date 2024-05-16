package com.greethy.nutrition.core.event;

import com.greethy.nutrition.core.domain.value.Owner;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FoodCreatedEvent {

    private String foodId;

    private String name;

    private String group;

    private String meal;

    private String description;

    private String recipe;

    private String tips;

    private Boolean open;

    private String imageUrl;

    private String videoUrl;

    private Owner owner;
}
