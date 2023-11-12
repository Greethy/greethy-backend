package com.greethy.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Token {

    @Id
    private String id;

    private String accessToken;

    private String refreshToken;

    private LocalDateTime createdAt;

    private boolean revoked;

}
