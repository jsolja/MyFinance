package com.foi.MyFinance.service;

import com.foi.MyFinance.entity.UserEntity;

import java.util.Optional;

public interface UserService
{
    Optional<UserEntity> findByUsername(String username);
}
