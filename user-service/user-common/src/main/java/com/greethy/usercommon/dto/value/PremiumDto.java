package com.greethy.usercommon.dto.value;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PremiumDto {

    @JsonProperty("is_premium")
    private Boolean isPremium;

    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("end_time")
    private LocalDateTime endTime;

}
