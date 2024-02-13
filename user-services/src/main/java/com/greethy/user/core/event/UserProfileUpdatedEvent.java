package com.greethy.user.core.event;

import com.greethy.user.infrastructure.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserProfileUpdatedEvent {

    private String userId;

    private Profile profile;

}
