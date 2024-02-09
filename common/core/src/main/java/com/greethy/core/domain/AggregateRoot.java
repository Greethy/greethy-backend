package com.greethy.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.logging.Logger;

@Getter
public abstract class AggregateRoot {

    protected String id;

    @Setter
    private int version = -1;

    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

}
