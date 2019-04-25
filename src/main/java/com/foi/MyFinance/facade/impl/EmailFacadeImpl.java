package com.foi.MyFinance.facade.impl;

import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.facade.EmailFacade;
import com.foi.MyFinance.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class EmailFacadeImpl implements EmailFacade
{
    @Autowired
    private EmailService emailService;

    @Override
    public void sendForgottenPasswordEmail(
            final UserEntity userEntity, final HttpServletRequest request)
    {
        emailService.sendForgottenPasswordEmail(userEntity, request);
    }
}
