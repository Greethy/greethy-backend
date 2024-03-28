package com.greethy.nutrition.api.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PageSupport<T>(List<T> content, int pageNumber, int size, long total) {

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
