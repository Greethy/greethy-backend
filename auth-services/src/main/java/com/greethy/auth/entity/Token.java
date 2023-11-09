package com.greethy.auth.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Token {

    @Id
    private String id;

    private String access;

    private String refresh;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private boolean revoked;

}
