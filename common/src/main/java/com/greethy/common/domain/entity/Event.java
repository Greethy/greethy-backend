package com.greethy.common.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class Event<T> implements Serializable {

    private String id;

    private String name;

    private String type;

    private String source;

    private T payload;

    private LocalDateTime createdAt;

}
