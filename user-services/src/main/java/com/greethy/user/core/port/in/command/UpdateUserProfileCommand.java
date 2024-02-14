package com.greethy.user.core.port.in.command;

import com.greethy.user.infrastructure.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserProfileCommand {

    @TargetAggregateIdentifier
    private String userId;

    private Profile profile;

}
