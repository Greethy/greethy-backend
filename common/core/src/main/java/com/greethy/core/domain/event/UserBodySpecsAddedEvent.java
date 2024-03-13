package com.greethy.core.domain.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBodySpecsAddedEvent {

    private String userId;

    private String bodySpecsId;

}
