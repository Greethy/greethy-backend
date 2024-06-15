package com.greethy.common.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DataResponse<T> implements Serializable {

    private T data;

    private String message;

    private int code;

    private Pagination pagination;

    public record Pagination(@JsonProperty("page_number") int pageNumber, int size, long total) {

        @JsonProperty("total_pages")
        public long totalPages() {
            return size > 0 ? (total - 1) / size + 1 : 0;
        }

        @JsonProperty
        public boolean first() {
            return pageNumber == 0;
        }

        @JsonProperty
        public boolean last() {
            return (long) (pageNumber + 1) * size >= total;
        }

    }

}
