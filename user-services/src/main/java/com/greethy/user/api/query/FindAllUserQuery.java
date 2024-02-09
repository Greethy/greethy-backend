package com.greethy.user.api.query;

import com.greethy.core.message.query.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FindAllUserQuery extends BaseQuery {

    private String username;

}
