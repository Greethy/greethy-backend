package com.greethy.user.core.domain.service;

import com.greethy.user.api.rest.dto.UserDto;
import com.greethy.user.api.rest.dto.response.UserLookupResponse;
import com.greethy.user.core.port.in.query.FindAllUserQuery;
import com.greethy.user.core.port.in.query.GetAllUserWithPageableQuery;
import com.greethy.user.core.port.out.FindUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserQueryHandler {

    private final ModelMapper mapper;

    private final FindUserPort findUserPort;

    @QueryHandler
    public UserLookupResponse findAllUser(FindAllUserQuery query) {
        List<UserDto> userDtos = findUserPort.findAll()
                .map(user -> mapper.map(user, UserDto.class))
                .collectList()
                .block();
        return UserLookupResponse.builder()
                .users(userDtos)
                .build();
    }

    @QueryHandler
    public UserLookupResponse getAllUserWithPageable(GetAllUserWithPageableQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        List<UserDto> users = findUserPort.findAll(pageable)
                .map(user -> mapper.map(user, UserDto.class))
                .collectList()
                .block();
        return UserLookupResponse.builder()
                .users(users)
                .build();
    }

}