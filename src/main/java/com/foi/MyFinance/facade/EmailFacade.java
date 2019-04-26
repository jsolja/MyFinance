package com.foi.MyFinance.facade;

import com.foi.MyFinance.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;

public interface EmailFacade
{
    void sendForgottenPasswordEmail(UserEntity userEntity, HttpServletRequest request);

    void sendActivationEmail(UserEntity userEntity, HttpServletRequest request);
}
