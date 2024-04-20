package com.greethy.user.core.port.out.user;

public interface CheckIfExistsUserPort {

    Boolean existsById(String id);

    Boolean existsByEmail(String email);

    Boolean existsByUsernameOrEmail(String username, String email);

}
