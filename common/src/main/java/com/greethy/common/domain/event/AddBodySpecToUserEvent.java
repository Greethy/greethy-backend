package com.greethy.common.domain.event;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddBodySpecToUserEvent {

    private String id;

    private String name;

    private String type;

    private String source;

    private Payload payload;

    private LocalDateTime createdAt;

    public record Payload(String bodySpecId, String username) {}

}
