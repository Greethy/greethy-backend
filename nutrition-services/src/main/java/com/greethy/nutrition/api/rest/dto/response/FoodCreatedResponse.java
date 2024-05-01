package com.greethy.nutrition.api.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FoodCreatedResponse(@JsonProperty("food_id") Object foodId) {}
