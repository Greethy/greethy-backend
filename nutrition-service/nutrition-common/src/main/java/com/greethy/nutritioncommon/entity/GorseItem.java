package com.greethy.nutritioncommon.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
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
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

    @JsonProperty("Labels")
    private List<String> labels;

    @JsonProperty("Comment")
    private String comment;

}
