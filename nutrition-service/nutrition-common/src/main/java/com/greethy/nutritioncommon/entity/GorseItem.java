package com.greethy.nutritioncommon.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GorseItem {

    @JsonProperty("ItemId")
    private String itemId;

    @JsonProperty("IsHidden")
    private boolean isHidden;

    @JsonProperty("Categories")
    private List<String> categories;

    @JsonProperty("Timestamp")
    private String timestamp;

    @JsonProperty("Labels")
    private List<String> labels;

    @JsonProperty("Comment")
    private String comment;

}
