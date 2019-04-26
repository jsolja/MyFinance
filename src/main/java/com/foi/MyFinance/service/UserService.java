package com.foi.MyFinance.service;

import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.model.UserModel;

import java.util.Optional;

public interface UserService
{
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByResetToken(String token);

    void createToken(UserEntity userEntity);

    void resetUserPassword(UserEntity userEntity, String newPassword);

    void activateUser(UserEntity userEntity);

    UserEntity createUser(UserModel userModel);
}
