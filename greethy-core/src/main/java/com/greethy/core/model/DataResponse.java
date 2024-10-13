package com.greethy.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class DataResponse<T> implements Serializable {

    private int code;

    private String message;

    private T data;

    public static <T> DataResponse<T> success(T data, String message) {
        return DataResponse.<T>builder()
                .message(message)
                .data(data)
                .build();
    }

    public static <T> DataResponse<T> failed(String message) {
        return DataResponse.<T>builder()
                .message(message)
                .build();
    }

}
