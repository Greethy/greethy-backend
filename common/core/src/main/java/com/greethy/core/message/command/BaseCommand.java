package com.greethy.core.message.command;

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
public abstract class BaseCommand extends Message {

    public BaseCommand(String id) {
        super(id);
    }

}
