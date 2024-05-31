package com.greethy.user.common.entity.value;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
public class Premium {

    @Field(name = "is_premium")
    private Boolean isPremium = Boolean.FALSE;

    @Field(name = "start_time")
    private LocalDateTime startTime;

    @Field(name = "end_time")
    private LocalDateTime endTime;
}
