package com.foi.emailservice.service;

import com.foi.emailservice.entity.UserEntity;

public interface EmailService
{
    boolean sendForgottenPasswordEmail(UserEntity userEntity);

    boolean sendActivationEmail(UserEntity userEntity);
}
