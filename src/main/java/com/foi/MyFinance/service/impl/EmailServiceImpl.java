package com.foi.MyFinance.service.impl;

import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.service.EmailService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class EmailServiceImpl implements EmailService
{
    @Override
    public void sendForgottenPasswordEmail(
            UserEntity userEntity, HttpServletRequest request)
    {

    }
}
