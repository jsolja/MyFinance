package com.foi.userservice.controller;

import com.foi.userservice.entity.UserEntity;
import com.foi.userservice.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

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
    private static final String MODEL_ATTRIBUTE_ERROR_MESSAGE = "Something went wrong with email sending.";
    private static final String MODEL_ATTRIBUTE_RESET_PASSWORD_MESSAGE = "This reset token is invalid or expired!";
    private static final String VIEW_RESET_PASSWORD = "reset-password";
    private static final String REDIRECT = "redirect";
    private static final String COLON = ":";
    private static final String URL_LOGIN = "/login?passwordChangeSuccessful";
    private static final String PARAMETER_TOKEN = "token";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_EMAIL = "email";

    private static final String EMAIL_SERVICE_URL = "http://email-service/send-forgotten-password-email";

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = VIEW_FORGOTTEN_PASSWORD, method = RequestMethod.GET)
    public String getViewForgottenPassword(final Model model)
    {
        return VIEW_FORGOTTEN_PASSWORD;
    }

    @RequestMapping(value = VIEW_FORGOTTEN_PASSWORD, method = RequestMethod.POST)
    public String postViewForgottenPassword(
            @RequestParam(PARAMETER_EMAIL)
            final
            String email,
            final Model model,
            final HttpServletRequest request)
    {
        final Optional<UserEntity> optionalUserEntity = userFacade.findByEmail(email);

        if (optionalUserEntity.isPresent())
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity entity = new HttpEntity(optionalUserEntity.get(), headers);
            ResponseEntity<Boolean> result = restTemplate.exchange(
                    EMAIL_SERVICE_URL,
                    HttpMethod.POST,
                    entity,
                    Boolean.class
            );

            if (!ObjectUtils.isEmpty(result.getBody()))
            {
                if (result.getBody())
                {
                    model.addAttribute(
                            MODEL_ATTRIBUTE_SUCCESS,
                            MODEL_ATTRIBUTE_SUCCESS_MESSAGE + email
                    );
                }
                else
                {
                    model.addAttribute(MODEL_ATTRIBUTE_ERROR, MODEL_ATTRIBUTE_ERROR_MESSAGE);
                }
            }
            else
            {
                model.addAttribute(MODEL_ATTRIBUTE_ERROR, MODEL_ATTRIBUTE_ERROR_MESSAGE);
            }
        }
        else
        {
            model.addAttribute(MODEL_ATTRIBUTE_ERROR, MODEL_ATTRIBUTE_EMAIL_MESSAGE);
        }
        return VIEW_FORGOTTEN_PASSWORD;
    }

    @RequestMapping(value = VIEW_RESET_PASSWORD, method = RequestMethod.GET)
    public String getViewResetPassword(
            @RequestParam(PARAMETER_TOKEN)
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
            @RequestParam(PARAMETER_TOKEN)
            final String token,
            @RequestParam(PARAMETER_PASSWORD)
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
