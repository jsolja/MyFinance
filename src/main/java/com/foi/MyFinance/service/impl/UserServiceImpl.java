package com.foi.MyFinance.service.impl;

import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.repository.UserRepository;
import com.foi.MyFinance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl
        implements UserService, UserDetailsService
{
    private static final String USERNAME_NOT_FOUND_EXCEPTION_MESSAGE = "Invalid username or password.";

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserEntity> findByUsername(final String username)
    {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(final String username)
    {
        final Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        if (!optionalUserEntity.isPresent())
        {
            throw new UsernameNotFoundException(USERNAME_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return optionalUserEntity.get();
    }
}
