package com.greethy.core.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataResponse<T> {

    private T data;

    private Boolean success;

}
