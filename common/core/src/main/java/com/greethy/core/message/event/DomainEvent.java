package com.greethy.core.message.event;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 *
 *
 * @author KienThanh
 * */
@Data
@Builder
@Document(collection = "event-storage")
public class DomainEvent {

    private String id;

    private Date timestamp;

    private String aggregateId;

    private String aggregateType;

    private String eventType;

    private BaseEvent event;

    private int version;

}
