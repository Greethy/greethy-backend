package com.greethy.usercommon.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GorseUser {

    @JsonProperty("UserId")
    private String userId;

    @JsonProperty("Labels")
    private List<String> labels;

    @JsonProperty("Comment")
    private String comment;

    @JsonProperty("Subscribe")
    private String subscribe;

}
