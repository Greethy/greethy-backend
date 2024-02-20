package com.greethy.user.core.port.in.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUserWithPageableQuery {

    private int page;

    private int size;

    private String sort;

}
