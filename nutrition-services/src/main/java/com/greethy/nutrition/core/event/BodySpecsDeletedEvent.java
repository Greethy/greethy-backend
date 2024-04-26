package com.greethy.nutrition.core.event;

import lombok.Data;

@Data
public class BodySpecsDeletedEvent {

    private String bodySpecsId;

    private String userId;

}
