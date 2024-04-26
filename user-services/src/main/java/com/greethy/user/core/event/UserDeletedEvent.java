package com.greethy.user.core.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDeletedEvent {

    private String userId;

    private String networkingId;

}
