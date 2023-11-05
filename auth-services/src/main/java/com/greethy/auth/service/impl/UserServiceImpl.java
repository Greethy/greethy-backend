package com.greethy.auth.service.impl;

import com.greethy.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link UserDetailsService} interface used to load user details
 * for authentication and authorization in Spring Security from a Data Source.
 *
 * @author ThanhKien
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Get user details by their username.
     *
     * @param username The username for which user details are to be loaded.
     * @return A {@link UserDetails} object representing the user details.
     * @throws UsernameNotFoundException If the user with the given username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found !"));
    }
}
