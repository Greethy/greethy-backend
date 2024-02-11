package com.greethy.core.domain;

import com.greethy.core.message.event.BaseEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Getter
public abstract class AggregateRoot {

    protected String id;

    @Setter
    private int version = -1;

    private final List<BaseEvent> changes = new ArrayList<>();

    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

    public List<BaseEvent> getUncommittedChanges() {
        return this.changes;
    }

    public void markChangesAsCommitted () {
        this.changes.clear();
    }

}
