package com.greethy.nutritioncommon.dto.request.query;

public record GetByPaginationQuery (Integer offset, Integer limit, String sort) {
}
