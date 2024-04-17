package com.greethy.user.core.domain.event;

import com.greethy.user.core.domain.value.PersonalDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserUpdatedEvent {

    private String userId;

    private String avatar;

    private String bannerImage;

    private String bio;

    private PersonalDetail personalDetail;

}
