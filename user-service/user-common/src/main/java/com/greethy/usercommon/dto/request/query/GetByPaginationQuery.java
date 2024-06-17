package com.greethy.usercommon.dto.request.query;

public record GetByPaginationQuery (Integer offset, Integer limit, String sort) {
}
