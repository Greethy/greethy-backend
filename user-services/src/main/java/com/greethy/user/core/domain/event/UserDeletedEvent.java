package com.greethy.user.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDeletedEvent {

    private String userId;

}
