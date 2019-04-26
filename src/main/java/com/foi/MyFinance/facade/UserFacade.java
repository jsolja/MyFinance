package com.foi.MyFinance.facade;

import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.model.UserModel;

import java.util.Optional;

public interface UserFacade
{
    void resetUserPassword(UserEntity userEntity, String newPassword);

    UserEntity createUser(UserModel userModel);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByToken(String token);
}
