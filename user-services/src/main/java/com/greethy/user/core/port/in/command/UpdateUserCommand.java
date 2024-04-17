package com.greethy.user.core.port.in.command;

import com.greethy.user.core.domain.value.PersonalDetail;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

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
