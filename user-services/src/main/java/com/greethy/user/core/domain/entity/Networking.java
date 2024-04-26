package com.greethy.user.core.domain.entity;

import com.greethy.user.core.event.NetworkingDeletedEvent;
import lombok.Data;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.EntityId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class Networking {

    @Id
    @EntityId
    private String id;

    private List<String> following;

    @Field(name = "total_following")
    private Integer totalFollowing = 0;

    private List<String> followers;

    @Field(name = "total_followers")
    private Integer totalFollowers = 0;

    public Networking(String id) {
        this.id = id;
    }

    @EventSourcingHandler
    void on(NetworkingDeletedEvent event) {
        AggregateLifecycle.markDeleted();
    }

}
