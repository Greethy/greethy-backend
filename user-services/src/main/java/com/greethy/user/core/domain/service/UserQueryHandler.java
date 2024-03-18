package com.greethy.user.core.domain.service;

import com.greethy.core.domain.exception.DomainErrorDetail;
import com.greethy.user.api.rest.dto.UserDto;
import com.greethy.user.api.rest.dto.response.UsersLookupResponse;
import com.greethy.user.core.domain.exception.NotFoundException;
import com.greethy.user.core.port.in.query.FindAllUserQuery;
import com.greethy.user.core.port.in.query.FindUserByIdQuery;
import com.greethy.user.core.port.in.query.FindUserByUsernameOrEmailQuery;
import com.greethy.user.core.port.in.query.GetAllUserWithPageableQuery;
import com.greethy.user.core.port.out.FindUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.axonframework.queryhandling.QueryExecutionException;
import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserQueryHandler {

    private final ModelMapper mapper;

    private final FindUserPort findUserPort;

    private static final Integer NOT_FOUND_STATUS = HttpStatus.NOT_FOUND.value();

    @QueryHandler
    public UsersLookupResponse handle(FindAllUserQuery query) {
        List<UserDto> userDtos = findUserPort.findAll()
                .map(user -> mapper.map(user, UserDto.class))
                .collectList()
                .block();
        return UsersLookupResponse.builder()
                .users(userDtos)
                .build();
    }

    @QueryHandler
    public UsersLookupResponse handle(GetAllUserWithPageableQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        List<UserDto> users = findUserPort.findAll(pageable)
                .map(user -> mapper.map(user, UserDto.class))
                .collectList()
                .block();
        return UsersLookupResponse.builder()
                .users(users)
                .build();
    }

    @QueryHandler
    public UserDto handle(FindUserByIdQuery query) {
        return findUserPort.findById(query.getUserId())
                .switchIfEmpty(Mono.error(new NotFoundException(NOT_FOUND_STATUS, "Cannot found user with id: " + query.getUserId())))
                .map(user -> mapper.map(user, UserDto.class))
                .block();
    }

    @QueryHandler
    public UserDto handle(FindUserByUsernameOrEmailQuery query) {
        return findUserPort.findByUsernameOrEmail(query.getUsernameOrEmail())
                .switchIfEmpty(Mono.error(new NotFoundException(NOT_FOUND_STATUS, "Cannot found user with: " + query.getUsernameOrEmail())))
                .map(user -> mapper.map(user, UserDto.class))
                .block();
    }



    @ExceptionHandler
    public void handleNotFoundException(NotFoundException exception) {
        var detail = DomainErrorDetail.builder()
                .name(exception.getClass().getName())
                .status(exception.getStatus())
                .message(exception.getMessage())
                .build();
        throw new QueryExecutionException(exception.getMessage(), exception, detail);
    }

}
