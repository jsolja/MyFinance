package com.foi.MyFinance.service;

import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.model.UserModel;

import java.util.Optional;

public interface UserService
{
    Optional<UserEntity> findByUsername(String username);

    UserEntity createUser(UserModel userModel);
}
