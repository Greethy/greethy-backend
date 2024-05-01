package com.greethy.user.core.domain.value;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GorseUser {

    @JsonProperty("UserId")
    private String id;

    @JsonProperty("Labels")
    private List<String> labels;

    @JsonProperty("Subscribe")
    private List<String> subscribe;

    @JsonProperty("Comment")
    private String comment;
}
