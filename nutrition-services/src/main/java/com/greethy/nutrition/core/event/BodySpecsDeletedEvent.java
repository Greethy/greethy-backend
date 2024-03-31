package com.greethy.nutrition.core.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BodySpecsDeletedEvent {

    private String bodySpecsId;

}
