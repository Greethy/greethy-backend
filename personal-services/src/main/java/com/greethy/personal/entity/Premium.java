package com.greethy.personal.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Premium {

    private boolean isPremium;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
