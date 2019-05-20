package com.foi.userservice.facade;

import com.foi.userservice.entity.UserEntity;
import com.foi.userservice.model.UserModel;

import java.util.Optional;

public interface UserFacade
{
    void activateUser(UserEntity userEntity);

    void resetUserPassword(UserEntity userEntity, String newPassword);

    void updateUser(UserModel userModel);

    UserEntity createUser(UserModel userModel);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByToken(String token);

    UserEntity getUserEntity();

    UserModel getUserModel();
}
