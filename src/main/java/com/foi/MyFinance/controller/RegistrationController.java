package com.foi.MyFinance.controller;

import com.foi.MyFinance.facade.UserFacade;
import com.foi.MyFinance.model.UserModel;
import com.foi.MyFinance.validation.RegistrationFieldsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class RegistrationController
{
    private static final String MODEL_ATTRIBUTE_USER_MODEL = "userModel";
    private static final String MODEL_ATTRIBUTE_SUCCESS = "successMessage";
    private static final String MODEL_ATTRIBUTE_SUCCESS_MESSAGE = "You have successfully registered!";

    private static final String VIEW_REGISTRATION = "registration";

    @Autowired
    private RegistrationFieldsValidator registrationFieldsValidator;

    @Autowired
    private UserFacade userFacade;

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
            final UserModel userModel, final BindingResult result, final Model model)
    {
        ValidationUtils.invokeValidator(registrationFieldsValidator, userModel, result);
        if (!result.hasErrors())
        {
            model.addAttribute(MODEL_ATTRIBUTE_SUCCESS, MODEL_ATTRIBUTE_SUCCESS_MESSAGE);
            userFacade.createUser(userModel);
        }
        return VIEW_REGISTRATION;
    }
}
