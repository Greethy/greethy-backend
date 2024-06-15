package com.greethy.nutritionquery.domain.service.impl;

import com.greethy.common.infra.component.i18n.Translator;
import com.greethy.nutritioncommon.constant.Constants;
import com.greethy.nutritioncommon.dto.request.query.GetByIdQuery;
import com.greethy.nutritioncommon.dto.response.BodySpecResponse;
import com.greethy.nutritioncommon.exception.NotFoundException;
import com.greethy.nutritionquery.domain.port.BodySpecPort;
import com.greethy.nutritionquery.domain.service.BodySpecQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class BodySpecQueryServiceImpl implements BodySpecQueryService {

    private final ModelMapper mapper;

    private final Translator translator;

    private final BodySpecPort mongoBodySpecPort;

    @Override
    public Mono<BodySpecResponse> findBodySpecById(GetByIdQuery query) {
        return Mono.fromSupplier(query::id)
                .switchIfEmpty(Mono.error(() -> {
                    String message = translator.getLocalizedMessage(Constants.MessageKeys.BODY_SPEC_NOT_FOUND);
                    return new NotFoundException(message);
                }))
                .flatMap(mongoBodySpecPort::findById)
                .map(bodySpec -> mapper.map(bodySpec, BodySpecResponse.class));
    }
}
