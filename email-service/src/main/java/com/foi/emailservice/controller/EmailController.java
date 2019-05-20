package com.foi.emailservice.controller;

import com.foi.emailservice.entity.UserEntity;
import com.foi.emailservice.facade.EmailFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jura Solja <jura.solja@ecx.io> ON 2019-05-20
 */
@RestController
public class EmailController
{
    @Autowired
    private EmailFacade emailFacade;

    @RequestMapping(value = "/send-forgotten-password-email")
    public Boolean sendForgottenPasswordEmail(
            @RequestBody
                    UserEntity userEntity, HttpServletRequest request)
    {
        return emailFacade.sendForgottenPasswordEmail(userEntity, request);
    }

    @RequestMapping(value = "/send-activation-email")
    public Boolean sendActivationEmail(
            @RequestBody
                    UserEntity userEntity, HttpServletRequest request)
    {
        return emailFacade.sendActivationEmail(userEntity, request);
    }
}
