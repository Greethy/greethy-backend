package com.greethy.feedbackcommon.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FeedbackType {

    LIKE("like"), STAR("star"), READ("read");

    private final String type;

}
