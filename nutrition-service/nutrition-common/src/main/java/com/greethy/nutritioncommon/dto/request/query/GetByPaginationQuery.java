package com.greethy.nutritioncommon.dto.request.query;

import lombok.Builder;

@Builder
public record GetByPaginationQuery (Integer offset, Integer limit, String sort) {
}
