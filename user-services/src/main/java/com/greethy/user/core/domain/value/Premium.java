package com.greethy.user.core.domain.value;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
public class Premium {

    private Boolean isPremium = Boolean.FALSE;

    @Field(name = "start_time")
    private LocalDateTime startTime;

    @Field(name = "end_time")
    private LocalDateTime endTime;

}
