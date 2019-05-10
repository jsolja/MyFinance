package com.foi.MyFinance.controller;

import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.facade.EmailFacade;
import com.foi.MyFinance.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class PasswordController
{
    private static final String VIEW_FORGOTTEN_PASSWORD = "forgotten-password";
    private static final String MODEL_ATTRIBUTE_TOKEN = "token";
    private static final String MODEL_ATTRIBUTE_SUCCESS = "successMessage";
    private static final String MODEL_ATTRIBUTE_SUCCESS_MESSAGE = "Email with reset link has been sent to ";
    private static final String MODEL_ATTRIBUTE_ERROR = "error";
    private static final String MODEL_ATTRIBUTE_EMAIL_MESSAGE = "This email does not belong to any account!";
    private static final String MODEL_ATTRIBUTE_RESET_PASSWORD_MESSAGE = "This reset token is invalid or expired!";
    private static final String VIEW_RESET_PASSWORD = "reset-password";
    private static final String REDIRECT = "redirect";
    private static final String COLON = ":";
    private static final String URL_LOGIN = "/login?passwordChangeSuccessful";

    @Autowired
    private EmailFacade emailFacade;

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = VIEW_FORGOTTEN_PASSWORD, method = RequestMethod.GET)
    public String getViewForgottenPassword(final Model model)
    {
        return VIEW_FORGOTTEN_PASSWORD;
    }

    @RequestMapping(value = VIEW_FORGOTTEN_PASSWORD, method = RequestMethod.POST)
    public String postViewForgottenPassword(
            @RequestParam("email")
            final
            String email,
            final Model model,
            final HttpServletRequest request)
    {
        final Optional<UserEntity> optionalUserEntity = userFacade.findByEmail(email);
        if (optionalUserEntity.isPresent())
        {
            model.addAttribute(
                    MODEL_ATTRIBUTE_SUCCESS,
                    MODEL_ATTRIBUTE_SUCCESS_MESSAGE + email
            );
            emailFacade.sendForgottenPasswordEmail(optionalUserEntity.get(), request);
        }
        else
        {
            model.addAttribute(MODEL_ATTRIBUTE_ERROR, MODEL_ATTRIBUTE_EMAIL_MESSAGE);
        }
        return VIEW_FORGOTTEN_PASSWORD;
    }

    @RequestMapping(value = VIEW_RESET_PASSWORD, method = RequestMethod.GET)
    public String getViewResetPassword(
            @RequestParam("token")
            final String token, final Model model)
    {
        final Optional<UserEntity> optionalUserEntity = userFacade.findByToken(token);
        if (!optionalUserEntity.isPresent())
        {
            model.addAttribute(MODEL_ATTRIBUTE_ERROR, MODEL_ATTRIBUTE_RESET_PASSWORD_MESSAGE);
        }
        model.addAttribute(MODEL_ATTRIBUTE_TOKEN, token);
        return VIEW_RESET_PASSWORD;
    }

    @RequestMapping(value = VIEW_RESET_PASSWORD, method = RequestMethod.POST)
    public String postViewResetPassword(
            @RequestParam("token")
            final String token,
            @RequestParam("password")
            final String password,
            final Model model)
    {
        final Optional<UserEntity> optionalUserEntity = userFacade.findByToken(token);
        if (!optionalUserEntity.isPresent())
        {
            model.addAttribute(MODEL_ATTRIBUTE_ERROR, MODEL_ATTRIBUTE_RESET_PASSWORD_MESSAGE);
            return VIEW_RESET_PASSWORD;
        }
        else
        {
            userFacade.resetUserPassword(optionalUserEntity.get(), password);
            return REDIRECT + COLON + URL_LOGIN;
        }
    }
}
