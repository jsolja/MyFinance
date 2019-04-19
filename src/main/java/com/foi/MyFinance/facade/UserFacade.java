package com.foi.MyFinance.facade;

import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.model.UserModel;

public interface UserFacade
{
    UserEntity createUser(UserModel userModel);
}
