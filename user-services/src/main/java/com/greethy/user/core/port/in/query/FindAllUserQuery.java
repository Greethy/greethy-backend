package com.greethy.user.core.port.in.query;

import com.greethy.core.message.query.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class FindAllUserQuery {

    private String username;

}
