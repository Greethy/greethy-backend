package com.greethy.user.core.port.in.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.greethy.user.core.domain.value.PersonalDetail;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserCommand {

    @TargetAggregateIdentifier
    private String userId;

    private String avatar;

    private String bannerImage;

    private String bio;

    private PersonalDetail personalDetail;
}
