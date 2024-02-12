package com.greethy.user.core.port.in.command;

import com.greethy.user.infrastructure.entity.Profile;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateUserProfileCommand {

    @TargetAggregateIdentifier
    private String userId;

    private Profile profile;

}
