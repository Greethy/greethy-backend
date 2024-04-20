package com.greethy.user.core.domain.event;

import com.greethy.user.core.domain.entity.Networking;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NetworkingCreatedEvent {

    private Networking networking;

}
