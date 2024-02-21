package com.greethy.user.core.port.out;

public interface CheckIfExistsUserPort {

    Boolean existsById(String id);

    Boolean existsByUsernameOrEmail(String username, String email);

}
