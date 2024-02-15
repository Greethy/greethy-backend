package com.greethy.user.api.error;

import org.springframework.http.HttpStatus;

public record UserBusinessError(
        String name,
        String message
) {}
