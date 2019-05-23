package com.foi.emailservice.controller;

import com.foi.emailservice.entity.UserEntity;
import com.foi.emailservice.facade.EmailFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController
{
    @Autowired
    private EmailFacade emailFacade;

    @RequestMapping(value = "/send-forgotten-password-email")
    public Boolean sendForgottenPasswordEmail(
            @RequestBody
                    UserEntity userEntity)
    {
        return emailFacade.sendForgottenPasswordEmail(userEntity);
    }

    @RequestMapping(value = "/send-activation-email")
    public Boolean sendActivationEmail(
            @RequestBody
                    UserEntity userEntity)
    {
        return emailFacade.sendActivationEmail(userEntity);
    }
}
