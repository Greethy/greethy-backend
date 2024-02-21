package com.greethy.core.domain.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 *
 *
 * @author KienThanh
 * */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Message {

    private String id;

}
