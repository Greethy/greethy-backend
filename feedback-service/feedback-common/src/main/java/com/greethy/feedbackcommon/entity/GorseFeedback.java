package com.greethy.feedbackcommon.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GorseFeedback {

    @JsonProperty("UserId")
    private String userId;

    @JsonProperty("ItemId")
    private String itemId;

    @JsonProperty("FeedbackType")
    private String feedbackType;

    @JsonProperty("Timestamp")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;

}
