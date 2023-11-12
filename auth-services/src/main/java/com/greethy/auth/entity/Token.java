package com.greethy.auth.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class Token {

    @Id
    private String id;

    private String accessToken;

    private String refreshToken;

    private LocalDate createdAt;

    private boolean revoked;

}
