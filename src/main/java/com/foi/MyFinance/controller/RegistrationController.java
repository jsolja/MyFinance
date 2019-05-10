package com.foi.MyFinance.controller;

import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.facade.EmailFacade;
import com.foi.MyFinance.facade.UserFacade;
import com.foi.MyFinance.model.UserModel;
import com.foi.MyFinance.validation.UserFieldsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RegistrationController
{
    private static final String MODEL_ATTRIBUTE_USER_MODEL = "userModel";
    private static final String MODEL_ATTRIBUTE_SUCCESS = "successMessage";
    private static final String MODEL_ATTRIBUTE_SUCCESS_MESSAGE = "You have successfully registered. Confirmation link has been sent to ";
    private static final String VIEW_REGISTRATION = "registration";
    private static final String VIEW_VERIFY_ACCOUNT = "verify-account";
    private static final String REDIRECT = "redirect";
    private static final String COLON = ":";
    private static final String URL_LOGIN_SUCCESS = "/login?successfulVerification";
    private static final String URL_LOGIN_ERROR = "/login?failedVerification";

    @Autowired
    private UserFieldsValidator userFieldsValidator;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private EmailFacade emailFacade;

    @RequestMapping(value = VIEW_REGISTRATION, method = RequestMethod.GET)
    public String getViewRegistration(final Model model)
    {
        model.addAttribute(MODEL_ATTRIBUTE_USER_MODEL, new UserModel());
        return VIEW_REGISTRATION;
    }

    @RequestMapping(value = VIEW_REGISTRATION, method = RequestMethod.POST)
    public String postViewRegistration(
            @ModelAttribute(MODEL_ATTRIBUTE_USER_MODEL)
            @Valid
            final UserModel userModel,
            final BindingResult result,
            final Model model,
            final HttpServletRequest request)
    {
        ValidationUtils.invokeValidator(userFieldsValidator, userModel, result);
        if (!result.hasErrors())
        {
            model.addAttribute(MODEL_ATTRIBUTE_SUCCESS, MODEL_ATTRIBUTE_SUCCESS_MESSAGE + userModel.getEmail());
            userFacade.createUser(userModel);
            final Optional<UserEntity> optionalUserEntity = userFacade.findByEmail(userModel.getEmail());
            emailFacade.sendActivationEmail(optionalUserEntity.get(), request);
        }
        return VIEW_REGISTRATION;
    }

    @RequestMapping(value = VIEW_VERIFY_ACCOUNT, method = RequestMethod.GET)
    public String getViewVerifyAccount(
            @RequestParam("token")
            final String token)
    {
        final Optional<UserEntity> optionalUserEntity = userFacade.findByToken(token);
        if (optionalUserEntity.isPresent())
        {
            userFacade.activateUser(optionalUserEntity.get());
            return REDIRECT + COLON + URL_LOGIN_SUCCESS;
        }
        return REDIRECT + COLON + URL_LOGIN_ERROR;
    }
}
