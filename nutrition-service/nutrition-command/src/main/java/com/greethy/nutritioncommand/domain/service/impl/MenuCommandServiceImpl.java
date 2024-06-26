package com.greethy.nutritioncommand.domain.service.impl;

import com.greethy.common.api.response.ObjectIdResponse;
import com.greethy.common.infra.component.i18n.Translator;
import com.greethy.nutritioncommand.domain.port.MenuPort;
import com.greethy.nutritioncommand.domain.service.MenuCommandService;
import com.greethy.nutritioncommon.constant.Constants;
import com.greethy.nutritioncommon.dto.request.command.CreateMenuCommand;
import com.greethy.nutritioncommon.dto.request.command.UpdateMenuCommand;
import com.greethy.nutritioncommon.dto.response.MenuResponse;
import com.greethy.nutritioncommon.entity.Menu;
import com.greethy.nutritioncommon.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuCommandServiceImpl implements MenuCommandService {

    private final MenuPort menuPort;
    private final ModelMapper mapper;
    private final Translator translator;

    @Override
    public Mono<ObjectIdResponse> createMenu(String username, CreateMenuCommand command) {
        return Mono.fromSupplier(() -> mapper.map(command, Menu.class))
                .doOnNext(menu -> menu.setOwnedBy(username))
                .flatMap(menuPort::save)
                .doOnSuccess(menu -> log.info("Menu: {} saved to MongoDB", menu.getId()))
                .map(menu -> new ObjectIdResponse(menu.getId()));
    }

    @Override
    public Mono<MenuResponse> updateMenu(String menuId, UpdateMenuCommand command) {
        return menuPort.findById(menuId)
                .switchIfEmpty(Mono.error(this::menuNotFound))
                //do update data
                .map(menu -> mapper.map(menu, MenuResponse.class));
    }

    @Override
    public Mono<Void> deleteMenu(String menuId) {
        return menuPort.deleteById(menuId)
                .doOnSuccess(unused -> log.info("Deleted menu with id: {}", menuId));
    }


    private NotFoundException menuNotFound() {
        var message = translator.getLocalizedMessage(Constants.MessageKeys.BODY_SPEC_NOT_FOUND);
        return new NotFoundException(message);
    }
}
