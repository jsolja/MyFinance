package com.foi.MyFinance.facade.impl;

import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.facade.UserFacade;
import com.foi.MyFinance.model.UserModel;
import com.foi.MyFinance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserFacadeImpl implements UserFacade
{
    @Autowired
    private UserService userService;

    @Override
    public UserEntity createUser(final UserModel userModel)
    {
        return userService.createUser(userModel);
    }

    @Override
    public Optional<UserEntity> findByEmail(final String email)
    {
        return userService.findByEmail(email);
    }

    @Override
    public Optional<UserEntity> findByUsername(final String username)
    {
        return userService.findByUsername(username);
    }

    @Override
    public Optional<UserEntity> findByResetToken(final String token)
    {
        return userService.findByResetToken(token);
    }
}
