package com.greethy.nutritioncommand.domain.port;

import com.greethy.nutritioncommon.dto.response.GorseResponse;
import com.greethy.nutritioncommon.entity.GorseItem;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GorseClientPort {

    Mono<GorseResponse> saveItem(GorseItem item);

    Mono<GorseResponse> saveItems(List<GorseItem> items);
}
