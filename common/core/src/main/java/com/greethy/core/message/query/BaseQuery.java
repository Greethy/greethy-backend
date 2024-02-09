package com.greethy.core.message.query;

import com.greethy.core.message.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author KienThanh
 * */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class BaseQuery extends Message {

    public BaseQuery (String id) {
        super(id);
    }

}
