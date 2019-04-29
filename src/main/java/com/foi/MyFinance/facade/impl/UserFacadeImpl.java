package com.foi.MyFinance.facade.impl;

import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.facade.UserFacade;
import com.foi.MyFinance.model.UserModel;
import com.foi.MyFinance.populator.UserPopulator;
import com.foi.MyFinance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserFacadeImpl implements UserFacade
{
    @Autowired
    private UserService userService;

    @Autowired
    private UserPopulator userPopulator;

    @Override
    public void activateUser(final UserEntity userEntity)
    {
        userService.activateUser(userEntity);
    }

    @Override
    public void resetUserPassword(final UserEntity userEntity, final String newPassword)
    {
        userService.resetUserPassword(userEntity, newPassword);
    }

    @Override
    public void updateUser(final UserModel userModel)
    {
        userService.updateUser(userModel);
    }

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
    public Optional<UserEntity> findByToken(final String token)
    {
        return userService.findByResetToken(token);
    }

    @Override
    public UserEntity getUserEntity()
    {
        return userService.getUserEntity();
    }

    @Override
    public UserModel getUserModel()
    {
        final UserEntity userEntity = getUserEntity();
        return userPopulator.populate(userEntity);
    }
}
