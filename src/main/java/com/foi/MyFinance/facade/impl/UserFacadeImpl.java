package com.foi.MyFinance.facade.impl;

import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.facade.UserFacade;
import com.foi.MyFinance.model.UserModel;
import com.foi.MyFinance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
