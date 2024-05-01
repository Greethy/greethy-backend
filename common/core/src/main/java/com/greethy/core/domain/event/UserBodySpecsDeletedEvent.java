package com.greethy.core.domain.event;

import lombok.Data;

@Data
public class UserBodySpecsDeletedEvent {

    private String userId;

    private String bodySpecsId;
}
