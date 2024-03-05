package com.greethy.nutrition.core.domain.aggregate;

import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class UserAggregate {

    @AggregateIdentifier
    private String userId;



}
