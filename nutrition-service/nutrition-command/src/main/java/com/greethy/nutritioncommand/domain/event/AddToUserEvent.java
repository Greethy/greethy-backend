package com.greethy.nutritioncommand.domain.event;

import java.io.Serializable;

public record AddToUserEvent (String addId, String username) implements Serializable {
}
