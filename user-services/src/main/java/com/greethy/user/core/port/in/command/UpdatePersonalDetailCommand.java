package com.greethy.user.core.port.in.command;

import com.greethy.user.core.domain.entity.PersonalDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePersonalDetailCommand {

    @TargetAggregateIdentifier
    private String userId;

    private PersonalDetail personalDetail;

}
