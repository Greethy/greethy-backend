package com.greethy.nutrition.core.domain.value;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class User {

    @JsonProperty("user_id")
    private String userId;

    private String avatar;

    private String username;

    private List<String> bodySpecsIds = new ArrayList<>();
}
