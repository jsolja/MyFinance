package com.foi.emailservice.facade;

import com.foi.emailservice.entity.UserEntity;

public interface EmailFacade
{
    boolean sendForgottenPasswordEmail(UserEntity userEntity);

    boolean sendActivationEmail(UserEntity userEntity);
}
