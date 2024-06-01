package com.greethy.userquery.domain.service.impl;

import com.greethy.core.component.Translator;
import com.greethy.usercommon.constant.Constant;
import com.greethy.usercommon.exception.NotFoundException;
import com.greethy.userquery.domain.port.UserPort;
import com.greethy.userquery.domain.service.UserQueryService;
import com.greethy.usercommon.dto.request.query.GetUserByIdQuery;
import com.greethy.usercommon.dto.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final ModelMapper mapper;

    private final Translator translator;

    private final UserPort mongoUserPort;

    public UserQueryServiceImpl(ModelMapper mapper,
                                Translator translator,
                                UserPort mongoUserPort) {
        this.mapper = mapper;
        this.translator = translator;
        this.mongoUserPort = mongoUserPort;
    }

    @Override
    public Mono<UserResponse> getUserById(GetUserByIdQuery query) {
        return mongoUserPort.findById(query.getUserId())
                .switchIfEmpty(Mono.error(() -> {
                    String exceptionMessage = translator.getLocalizedMessage(Constant.MessageKeys.USER_NOT_FOUND);
                    return new NotFoundException(exceptionMessage);
                })).map(user -> mapper.map(user, UserResponse.class));
    }
}
