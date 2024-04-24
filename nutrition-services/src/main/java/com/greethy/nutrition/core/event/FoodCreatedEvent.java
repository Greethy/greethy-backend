package com.greethy.nutrition.core.event;

import com.greethy.nutrition.core.domain.value.Owner;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FoodCreatedEvent {

    private String foodId;

    private String name;

    private String description;

    private String recipe;

    private String tips;

    private Boolean open;

    private String imageUrl;

    private String videoUrl;

    private LocalDateTime createdAt;

    private Owner owner;
}
