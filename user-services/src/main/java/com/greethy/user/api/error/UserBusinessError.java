package com.greethy.user.api.error;
public record UserBusinessError(
        String name,
        String message
) {}
