package com.foi.emailservice.facade;

import com.foi.emailservice.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;

public interface EmailFacade
{
    boolean sendForgottenPasswordEmail(UserEntity userEntity, HttpServletRequest request);

    boolean sendActivationEmail(UserEntity userEntity, HttpServletRequest request);
}