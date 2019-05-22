package com.foi.userservice.facade.impl;

import com.foi.userservice.entity.UserEntity;
import com.foi.userservice.facade.UserFacade;
import com.foi.userservice.model.TransactionListModel;
import com.foi.userservice.model.UserModel;
import com.foi.userservice.populator.UserPopulator;
import com.foi.userservice.service.UserService;
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
    public void createToken(UserEntity userEntity)
    {
        userService.createToken(userEntity);
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

    @Override
    public boolean updateBalance(TransactionListModel transactionListModel)
    {
        return userService.updateBalance(transactionListModel);
    }
}
