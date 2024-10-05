package com.greethy.core.domain;

public abstract class Command extends Message {

    public Command(String id) {
        super(id);
    }

}
