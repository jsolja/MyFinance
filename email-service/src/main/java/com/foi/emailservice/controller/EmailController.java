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
    private static final String MAPPING_SEND_FORGOTTEN_PASSWORD_EMAIL = "/send-forgotten-password-email";

    @Autowired
    private EmailFacade emailFacade;

    @RequestMapping(value = MAPPING_SEND_FORGOTTEN_PASSWORD_EMAIL)
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
