package com.greethy.nutrition.core.domain.value;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Item {

    @JsonProperty("ItemId")
    private String itemId;

    @JsonProperty("IsHidden")
    private Boolean isHidden;

    @JsonProperty("Categories")
    private List<String> categories;

    @JsonProperty("Timestamp")
    private Date timestamp;

    @JsonProperty("Labels")
    private List<String> labels;
}
