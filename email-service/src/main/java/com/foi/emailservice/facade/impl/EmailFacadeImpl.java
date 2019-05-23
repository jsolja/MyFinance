package com.foi.emailservice.facade.impl;

import com.foi.emailservice.entity.UserEntity;
import com.foi.emailservice.facade.EmailFacade;
import com.foi.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class EmailFacadeImpl implements EmailFacade
{
    @Autowired
    private EmailService emailService;

    @Override
    public boolean sendForgottenPasswordEmail(final UserEntity userEntity)
    {
        return emailService.sendForgottenPasswordEmail(userEntity);
    }

    @Override
    public boolean sendActivationEmail(final UserEntity userEntity)
    {
        return emailService.sendActivationEmail(userEntity);
    }
}
