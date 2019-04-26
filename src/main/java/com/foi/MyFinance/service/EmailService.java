package com.foi.MyFinance.service;

import com.foi.MyFinance.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;

public interface EmailService
{
    void sendForgottenPasswordEmail(UserEntity userEntity, HttpServletRequest request);

    void sendActivationEmail(UserEntity userEntity, HttpServletRequest request);
}
