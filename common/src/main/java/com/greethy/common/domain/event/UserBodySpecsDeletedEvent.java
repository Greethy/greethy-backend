package com.greethy.common.domain.event;

import lombok.Data;

@Data
public class UserBodySpecsDeletedEvent {

    private String userId;

    private String bodySpecsId;
}
