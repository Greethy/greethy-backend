package com.greethy.user.core.port.out.read;

public interface CheckIfExistsUserPort {

    Boolean existsById(String id);

    Boolean existsByEmail(String email);

    Boolean existsByUsernameOrEmail(String username, String email);

}
