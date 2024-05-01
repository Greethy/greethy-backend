package com.greethy.user.core.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NetworkingDeletedEvent {

    private String networkingId;
}
