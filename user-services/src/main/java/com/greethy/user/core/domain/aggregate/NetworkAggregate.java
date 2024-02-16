package com.greethy.user.core.domain.aggregate;

import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class NetworkAggregate {

    @AggregateIdentifier
    private String networkId;

}
