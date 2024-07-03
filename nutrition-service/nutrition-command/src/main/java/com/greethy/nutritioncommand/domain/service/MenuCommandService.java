package com.greethy.nutritioncommand.domain.service;

import com.greethy.common.api.response.ObjectIdResponse;
import com.greethy.nutritioncommon.dto.request.command.CreateMenuCommand;
import com.greethy.nutritioncommon.dto.request.command.UpdateMenuCommand;
import com.greethy.nutritioncommon.dto.response.MenuResponse;
import reactor.core.publisher.Mono;

public interface MenuCommandService {

    Mono<ObjectIdResponse> createMenu(String username, CreateMenuCommand command);

    Mono<MenuResponse> updateMenu(String menuId, UpdateMenuCommand command);

    Mono<Void> deleteMenu(String menuId);

}
